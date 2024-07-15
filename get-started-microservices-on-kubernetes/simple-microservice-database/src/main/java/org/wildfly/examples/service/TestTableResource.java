package org.wildfly.examples.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.wildfly.examples.model.TestTable;

import java.util.List;

@RequestScoped
@Path("/test-table")
public class TestTableResource {

	@PersistenceContext(type = PersistenceContextType.EXTENDED,unitName = "primary")
	private EntityManager em;

	public void createOrUpdateRow(TestTable row) {
		if (!em.contains(row)) {
			row = em.merge(row);
		}
		em.persist(row);
	}

	public List<TestTable> getAllRows() {
		return em.createQuery("SELECT t FROM TestTable t", TestTable.class).getResultList();
	}

	@Transactional(Transactional.TxType.REQUIRED)
	@POST
	@Produces({"application/json"})
	@Path("insert/{field1}/{field2}")
	public Response insertIntoTestTable(
			@PathParam("field1") @DefaultValue("some data 1") String field1,
			@PathParam("field2") @DefaultValue("some data 2") String field2) throws Exception {
		TestTable testTable = null;
		try {
			testTable = new TestTable();
			testTable.setField1(field1);
			testTable.setField2(field2);

			createOrUpdateRow(testTable);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		return Response.ok(testTable).build();
	}

	@GET
	@Produces({"application/json"})
	@Path("list")
	public List<TestTable> getAllRowsFromTestTable() {
		try {
			List<TestTable> allRows = getAllRows();
			return allRows;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
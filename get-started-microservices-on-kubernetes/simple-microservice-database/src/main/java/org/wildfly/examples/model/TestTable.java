package org.wildfly.examples.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@SuppressWarnings("serial")
@Entity
@XmlRootElement(name = "test-table")
public class TestTable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String field1;

	private String field2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	@XmlElement
	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TestTable testTable = (TestTable) o;
		return Objects.equals(id, testTable.id) && Objects.equals(field1, testTable.field1) && Objects.equals(field2, testTable.field2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, field1, field2);
	}
}
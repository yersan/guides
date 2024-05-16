# Generate broker's key pair using obtained OpenShift cluster domain for `apps`
keytool -genkeypair -alias broker -keyalg RSA -storetype PKCS12 -keystore broker.ks -storepass changeit -validity 365 -dname "CN=*.`oc get ingresscontroller default -o jsonpath='{.status.domain}' -n openshift-ingress-operator `, OU=My Org Unit, O=My Organization, L=My City, S=My State, C=My Country"
# Generate key pair for reactive messaging application
keytool -genkeypair -alias client -keyalg RSA -storetype PKCS12 -keystore client.ks -storepass changeit -validity 365 -dname "CN=localhost, OU=My Org Unit, O=My Organization, L=My City, S=My State, C=My Country"
# Export broker certificate
keytool -export -alias broker -file broker.cert -keystore broker.ks -storepass changeit
# Export client certificate
keytool -export -alias client -file client.cert -keystore client.ks -storepass changeit
# Import broker and client certificate into a single truststore for simplicity
keytool -import -v -trustcacerts -alias client -file client.cert -keystore client.ts -storepass changeit -noprompt
keytool -import -v -trustcacerts -alias broker -file broker.cert -keystore client.ts -storepass changeit -noprompt
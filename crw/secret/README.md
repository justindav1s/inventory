
https://access.redhat.com/documentation/en-us/red_hat_codeready_workspaces/2.3/html-single/installation_guide/index#understanding-codeready-workspaces-server-advanced-configuration-using-the-operator_advanced-configuration-options-for-the-codeready-workspaces-server-component

CHE_WORKSPACE_PROVISION_SECRET_LABELS

app.kubernetes.io/part-of=che.eclipse.org,app.kubernetes.io/component=workspace-secret

Defines comma-separated list of labels for selecting secrets from a user namespace, which will be mount into workspace containers as a files or env variables. Only secrets that match ALL given labels will be selected.


CHE_SYSTEM_ADMIN__NAME

admin

Grant system permission for 'che.admin.name' user. If the user already exists it’ll happen on component startup, if not - during the first login when user is persisted in the database.


CHE_SYSTEM_ADMIN__NAME property (the default is admin).

oc delete project tester1-codeready
oc new-project tester1-codeready

oc delete secret user-privatekey
oc create secret generic user-privatekey --from-file=id_rsa=/Users/justin/.ssh/id_rsa
oc label secret user-privatekey app.kubernetes.io/part-of=che.eclipse.org
oc label secret user-privatekey app.kubernetes.io/component=workspace-secret
oc annotate secret user-privatekey che.eclipse.org/automount-workspace-secret=true
oc annotate secret user-privatekey che.eclipse.org/mount-as=file
oc annotate secret user-privatekey che.eclipse.org/mount-path=/home/theia/.ssh

https://codeready-openshift-workspaces.apps.ocp4.datr.eu/dashboard


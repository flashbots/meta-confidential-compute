## GCP documentation
https://cloud.google.com/confidential-computing/confidential-vm/docs/attestation
https://cloud.google.com/confidential-computing/confidential-vm/docs/create-a-confidential-vm-instance
https://cloud.google.com/compute/docs/images/create-custom#guest-os-features
https://cloud.google.com/compute/docs/import/import-existing-image#gcloud-and-gcloud-storage
https://cloud.google.com/iap/docs/using-tcp-forwarding

## Deploy the yocto image to GCP
`deploy.sh` is a helper script to deploy yocto vm images to GCP
It will not add a firewall configuration and expects you to either have set up a proper `default` network or supply your own network via the `$NETWORK` env var.

```
VM_NAME=yocto-test ./deploy.sh
```

### port access and firewall rules
you can open ports in the default network like this
```
gcloud compute firewall-rules create cvm --network default --allow tcp:8080
```
if you enable SSH access on a debug-tweaked image, be sure to limit ssh ingress to an IP range only you or others within the company have access.
```
gcloud compute firewall-rules create allow-ssh-ingress-from-iap \
  --direction=INGRESS \
  --action=allow \
  --rules=tcp:22 \
  --source-ranges=35.235.240.0/20
```





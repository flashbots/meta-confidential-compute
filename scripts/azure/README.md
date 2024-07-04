## Deploy the yocto image to azure
```
./deploy.sh /path/to/disk.vhd resource_and_disk_and_vm_name region Standard_EC4eds_v5 dev_server_ip
```

`deploy.sh` is a helper script to deploy yocto vm images to azure
It creates its own resource group in which it will upload the vm image.
It will add a firewall configuration that blocks ssh access except from the (dev server) IP provided.
You can conveniently ssh into the vm after deployment from anywhere, (so long as you have ssh access to the dev server) via:
```
ssh -J user@dev_server_ip root@azure_vm_ip
```
The firwall also opens common ports for the rbuilder scenario, i.e. 8454, 8551, 8645, 8745 and 30303

If you want to remove your vm, you can just delete the whole resource group that has been created:

```
az group delete --name resource_and_disk_and_vm_name
```

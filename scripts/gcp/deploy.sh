#!/bin/sh

# Check if VM_NAME is set
if [ -z "$VM_NAME" ]; then
  echo "Error: VM_NAME must be set before running this script."
  exit 1
fi

# Set STORAGE_NAME to VM_NAME if not set
: ${STORAGE_NAME:=$VM_NAME}

# Use given defaults for MACHINE_TYPE and ZONE if not set
: ${MACHINE_TYPE:=c3-standard-4}
: ${ZONE:=europe-west4-b}
: ${NETWORK:=default}

gcloud storage buckets create gs://${STORAGE_NAME}
gcloud storage cp tmp/deploy/images/tdx-gcp/cvm-image-azure-tdx-gcp.rootfs.wic.tar.gz gs://${STORAGE_NAME}
gcloud compute images create ${VM_NAME} --source-uri gs://${STORAGE_NAME}/cvm-image-azure-tdx-gcp.rootfs.wic.tar.gz --guest-os-features=UEFI_COMPATIBLE,VIRTIO_SCSI_MULTIQUEUE,GVNIC,TDX_CAPABLE
gcloud compute instances create ${VM_NAME} --network=${NETWORK} --confidential-compute-type=TDX --machine-type=${MACHINE_TYPE} --maintenance-policy=TERMINATE --image ${VM_NAME} --zone=${ZONE} --metadata serial-port-enable=TRUE

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto:"
# adding one config to the SRC_URI will add all others in this folder as well
SRC_URI += "file://tdx-guest.scc"

#KERNEL_FEATURES:append=" features/security/security.scc"
KERNEL_FEATURES:append=" features/scsi/disk.scc cfg/fs/ext4.scc disk-encryption.scc security-mitigations.scc"

KMACHINE:sev-snp-azure ?= "common-pc-64"
COMPATIBLE_MACHINE:sev-snp-azure = "sev-snp-azure"

KMACHINE:tdx-azure ?= "common-pc-64"
COMPATIBLE_MACHINE:tdx-azure = "tdx-azure"

KMACHINE:tdx-gcp ?= "common-pc-64"
COMPATIBLE_MACHINE:tdx-gcp = "tdx-gcp"

KMACHINE:tdx-qemu ?= "common-pc-64"
COMPATIBLE_MACHINE:tdx-qemu = "tdx-qemu"

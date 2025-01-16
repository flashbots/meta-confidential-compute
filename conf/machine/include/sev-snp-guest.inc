require conf/machine/include/x86.inc

MACHINEOVERRIDES =. "sev-snp-guest:"

# Provide the tdx guest driver kernel module for all images
KERNEL_FEATURES:append:pn-linux-yocto-tiny = " sev-snp-guest.scc"
KERNEL_FEATURES:append:pn-linux-yocto = " sev-snp-guest.scc"
KERNEL_FEATURES:append:pn-linux-yocto-rt = " sev-snp-guest.scc"

require conf/machine/include/x86.inc

MACHINEOVERRIDES =. "tdx-guest:"

# Provide the tdx guest driver kernel module for all images
KERNEL_FEATURES:append:pn-linux-yocto-tiny = " tdx-guest.scc"
KERNEL_FEATURES:append:pn-linux-yocto = " tdx-guest.scc"
KERNEL_FEATURES:append:pn-linux-yocto-rt = " tdx-guest.scc"

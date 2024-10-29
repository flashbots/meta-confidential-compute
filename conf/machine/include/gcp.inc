MACHINEOVERRIDES =. "gcp:"

# gcp tdx depends on kvm and virtio
KERNEL_FEATURES:append:pn-linux-yocto-tiny = " gcp.scc cfg/virtio.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES:append:pn-linux-yocto = " gcp.scc cfg/virtio.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES:append:pn-linux-yocto-rt = " gcp.scc cfg/virtio.scc cfg/paravirt_kvm.scc"

# gcp is not compatible with udhcpc, needs dhcpcd
MACHINE_ESSENTIAL_EXTRA_RDEPENDS:append = " dhcpcd"


# doesnt work, no idea why
# IMAGE_FSTYPES:append = " wic.tar.gz"

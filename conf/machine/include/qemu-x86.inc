KERNEL_FEATURES:append:pn-linux-yocto-tiny = " cfg/virtio.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES:append:pn-linux-yocto = " cfg/virtio.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES:append:pn-linux-yocto-rt = " cfg/virtio.scc cfg/paravirt_kvm.scc"

IMAGE_FSTYPES:append:core-image-minimal = " wic.qcow2"

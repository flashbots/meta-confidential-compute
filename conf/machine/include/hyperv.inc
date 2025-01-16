MACHINEOVERRIDES =. "hyperv:"

# Provide the tdx guest driver kernel module for all images
KERNEL_FEATURES:append:pn-linux-yocto-tiny = " hyperv.scc features/tpm/tpm-2.0.scc features/tpm/tpm-2.0-crb.scc"
KERNEL_FEATURES:append:pn-linux-yocto = " hyperv.scc features/tpm/tpm-2.0.scc features/tpm/tpm-2.0-crb.scc"
KERNEL_FEATURES:append:pn-linux-yocto-rt = " hyperv.scc features/tpm/tpm-2.0.scc features/tpm/tpm-2.0-crb.scc"


MACHINE_FEATURES:append = " tpm2"

# doesnt work, no idea why
# IMAGE_FSTYPES:append:core-image-minimal = " wic.vhd"

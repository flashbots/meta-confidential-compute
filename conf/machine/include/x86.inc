DEFAULTTUNE ?= "x86-64-v3"

require conf/machine/include/x86/tune-x86-64-v3.inc

KERNEL_IMAGETYPE = "bzImage"
SERIAL_CONSOLES ?= "115200;ttyS0 115200;ttyS1"

MACHINE_FEATURES:append = "x86 pci"

do_image_wic[depends] += "syslinux:do_populate_sysroot syslinux-native:do_populate_sysroot mtools-native:do_populate_sysroot dosfstools-native:do_populate_sysroot"

# For runqemu
IMAGE_CLASSES += "qemuboot"

QB_CPU:x86-64 ?= "-cpu IvyBridge -machine q35,i8042=off"
QB_CPU_KVM:x86-64 ?= "-cpu IvyBridge -machine q35,i8042=off"

QB_SYSTEM_NAME = "qemu-system-x86_64"

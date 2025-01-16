SUMMARY = "Placeholder image to using IMAGE_FSTYPES to create wic based images of the initramfs."

IMAGE_INSTALL = ""

IMAGE_LINGUAS = " "

# override vhd conversion cmd - azure rquirements of virtual size aligned to 1 MiB
CONVERSION_CMD:vhd:prepend = "truncate -s %1MiB ${IMAGE_NAME}.wic; \
                              qemu-img convert -O vpc -o subformat=fixed,force_size ${IMAGE_NAME}.wic ${IMAGE_NAME}.wic.vhd; \
                              echo "

CONVERSION_CMD:tar = "tar --format=oldgnu -Scf ${IMAGE_NAME}.${type}.tar --transform='s|${IMAGE_NAME}.${type}|disk.raw|' ${IMAGE_NAME}.${type}"
CONVERSION_DEPENDS_tar = "tar-native"
CONVERSIONTYPES:append = " tar"

IMAGE_FSTYPES:append = " wic.tar.gz wic.vhd wic.qcow2"

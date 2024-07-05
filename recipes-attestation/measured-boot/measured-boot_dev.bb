inherit go-mod
LICENSE = "CLOSED"

GO_IMPORT = "github.com/flashbots/measured-boot"
SRC_URI = "git://${GO_IMPORT};protocol=https;branch=main"
SRCREV = "${AUTOREV}"

GO_LINKSHARED = ""

# reproducible builds
INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INHIBIT_PACKAGE_STRIP = '1'
GO_EXTRA_LDFLAGS:append = " -s -w -buildid="

RDEPENDS:${PN}-dev += "bash"

do_compile[network] = "1"


SUMMARY = "TDX Quote Generation Tool"
HOMEPAGE = "https://github.com/MoeMahhouk/go-tdx-quote-gen"
LICENSE = "CLOSED"

inherit go-mod

GO_IMPORT = "github.com/MoeMahhouk/go-tdx-quote-gen"
SRC_URI = "git://${GO_IMPORT};protocol=https;branch=main"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"


# Add build flags
GO_BUILDFLAGS += "-trimpath"

# Add linker flags
GO_LDFLAGS += "-buildmode=pie"

do_compile[network] = "1"

# reproducible builds
INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INHIBIT_PACKAGE_STRIP = '1'

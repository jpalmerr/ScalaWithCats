import java.net.URLEncoder

def tvGuideMetaQuery(): String = {
  val jsonId = "2/3211/3803#001"
  s"""
     |query {
     |  versions(filter: { legacyId: "$jsonId" ,
     |            tiers: ["FREE", "PAID"] }) {
     |    title {
     |      availableNow
     |      tier
     |      ccid
     |      titleType
     |      title
     |      synopses{epg}
     |      brandLegacyId
     |      latestAvailableVersion {
     |                compliance {
     |                  displayableGuidance
     |                }
     |              }
     |      brand {
     |                availableNow
     |                title
     |                genres {
     |                  name
     |                }
     |                synopses {
     |                  epg
     |                }
     |              }
     |              ... on Episode {
     |                episodeNumber
     |                series {
     |                  seriesNumber
     |                }
     |              }
     |              ... on Film {
     |                genres {
     |                  name
     |                }
     |              }
     |              ... on Special {
     |                genres {
     |                  name
     |                }
     |              }
     |    }
     |  }
     |}
     |""".stripMargin
}

URLEncoder.encode(tvGuideMetaQuery(), "UTF-8")


def myq(): String = {
  val jsonId = "2/3211/3803#001"
  s"""
     |query {
     |  versions(filter: { legacyId: "$jsonId" ,
     |            tiers: ["FREE", "PAID"] }) {
     |    title {
     |      availableNow
     |      tier
     |      ccid
     |      titleType
     |      title
     |      synopses{epg}
     |      brandLegacyId
     |      latestAvailableVersion {
     |                compliance {
     |                  displayableGuidance
     |                }
     |              }
     |      brand {
     |                availableNow
     |                title
     |                genres {
     |                  name
     |                }
     |                synopses {
     |                  epg
     |                }
     |              }
     |              ... on Episode {
     |                episodeNumber
     |                series {
     |                  seriesNumber
     |                }
     |              }
     |              ... on Film {
     |                genres {
     |                  name
     |                }
     |              }
     |              ... on Special {
     |                genres {
     |                  name
     |                }
     |              }
     |    }
     |  }
     |}
     |""".stripMargin
}

myq()
class Feature(val name: String)

val features: Set[Feature] = Set(new Feature(("mpeg-dash")), new Feature("widevine"))

features.map(_.name)

features.map(_.name).toString()
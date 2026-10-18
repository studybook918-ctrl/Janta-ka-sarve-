package com.example.data

object UPData {

    val districts = listOf(
        District("lucknow", "लखनऊ", "Lucknow"),
        District("varanasi", "वाराणसी", "Varanasi"),
        District("gorakhpur", "गोरखपुर", "Gorakhpur"),
        District("prayagraj", "प्रयागराज", "Prayagraj"),
        District("kanpur_nagar", "कानपुर नगर", "Kanpur Nagar"),
        District("agra", "आगरा", "Agra"),
        District("meerut", "मेरठ", "Meerut"),
        District("ayodhya", "अयोध्या", "Ayodhya"),
        District("bareilly", "बरेली", "Bareilly"),
        District("aligarh", "अलीगढ़", "Aligarh"),
        District("moradabad", "मुरादाबाद", "Moradabad"),
        District("saharanpur", "सहारनपुर", "Saharanpur"),
        District("ghaziabad", "गाजियाबाद", "Ghaziabad"),
        District("gautam_buddha_nagar", "गौतम बुद्ध नगर (नोएडा)", "Gautam Buddha Nagar"),
        District("jhansi", "झांसी", "Jhansi"),
        District("mathura", "मथुरा", "Mathura"),
        District("muzaffarnagar", "मुजफ्फरनगर", "Muzaffarnagar"),
        District("rampur", "रामपुर", "Rampur"),
        District("shahjahanpur", "शाहजहांपुर", "Shahjahanpur"),
        District("firozabad", "फिरोजाबाद", "Firozabad"),
        District("etawah", "इटावा", "Etawah"),
        District("mainpuri", "मैनपुरी", "Mainpuri"),
        District("budaun", "बदायूँ", "Budaun"),
        District("pilibhit", "पीलीभीत", "Pilibhit"),
        District("lakhimpur_kheri", "लखीमपुर खीरी", "Lakhimpur Kheri"),
        District("sitapur", "सीतापुर", "Sitapur"),
        District("hardoi", "हरदोई", "Hardoi"),
        District("unnao", "उन्नाव", "Unnao"),
        District("rae_bareli", "रायबरेली", "Rae Bareli"),
        District("amethi", "अमेठी", "Amethi"),
        District("sultanpur", "सुल्तानपुर", "Sultanpur"),
        District("barabanki", "बाराबंकी", "Barabanki"),
        District("bahraich", "बहराइच", "Bahraich"),
        District("shravasti", "श्रावस्ती", "Shravasti"),
        District("balrampur", "बलरामपुर", "Balrampur"),
        District("gonda", "गोंडा", "Gonda"),
        District("siddharthnagar", "सिद्धार्थनगर", "Siddharthnagar"),
        District("basti", "बस्ती", "Basti"),
        District("sant_kabir_nagar", "संत कबीर नगर", "Sant Kabir Nagar"),
        District("maharajganj", "महराजगंज", "Maharajganj"),
        District("kushinagar", "कुशीनगर", "Kushinagar"),
        District("deoria", "देवरिया", "Deoria"),
        District("azamgarh", "आजमगढ़", "Azamgarh"),
        District("mau", "मऊ", "Mau"),
        District("ballia", "बलिया", "Ballia"),
        District("jaunpur", "जौनपुर", "Jaunpur"),
        District("ghazipur", "गाजीपुर", "Ghazipur"),
        District("chandauli", "चंदौली", "Chandauli"),
        District("mirzapur", "मिर्जापुर", "Mirzapur"),
        District("sonbhadra", "सोनभद्र", "Sonbhadra"),
        District("bhadohi", "भदोही", "Bhadohi"),
        District("kaushambi", "कौशाम्बी", "Kaushambi"),
        District("pratapgarh", "प्रतापगढ़", "Pratapgarh"),
        District("fatehpur", "फतेहपुर", "Fatehpur"),
        District("kanpur_dehat", "कानपुर देहात", "Kanpur Dehat"),
        District("jaloun", "जालौन", "Jalaun"),
        District("hamirpur", "हमीरपुर", "Hamirpur"),
        District("mahoba", "महोबा", "Mahoba"),
        District("banda", "बांदा", "Banda"),
        District("chitrakoot", "चित्रकूट", "Chitrakoot"),
        District("lalitpur", "ललितपुर", "Lalitpur"),
        District("farrukhabad", "फर्रुखाबाद", "Farrukhabad"),
        District("kannauj", "कन्नौज", "Kannauj"),
        District("auraiya", "औरैया", "Auraiya"),
        District("kasganj", "कासगंज", "Kasganj"),
        District("hathras", "हाथरस", "Hathras"),
        District("etai", "एटा", "Etah"),
        District("sambhal", "सम्भल", "Sambhal"),
        District("amroha", "अमरोहा", "Amroha"),
        District("bijnor", "बिजनौर", "Bijnor"),
        District("baghpat", "बागपत", "Baghpat"),
        District("shamli", "शामली", "Shamli"),
        District("hapur", "हापुड़", "Hapur"),
        District("bulandshahr", "बुलंदशहर", "Bulandshahr")
    ).sortedBy { it.nameHi }

    val sampleConstituencies = listOf(
        // Lucknow
        Constituency("168", 168, "मलिहाबाद (SC)", "Malihabad (SC)", "lucknow"),
        Constituency("169", 169, "बक्शी का तालाब", "Bakshi Kaa Talab", "lucknow"),
        Constituency("170", 170, "सरोजिनी नगर", "Sarojini Nagar", "lucknow"),
        Constituency("171", 171, "लखनऊ पश्चिम", "Lucknow West", "lucknow"),
        Constituency("172", 172, "लखनऊ उत्तर", "Lucknow North", "lucknow"),
        Constituency("173", 173, "लखनऊ पूर्व", "Lucknow East", "lucknow"),
        Constituency("174", 174, "लखनऊ मध्य", "Lucknow Central", "lucknow"),
        Constituency("175", 175, "लखनऊ कैंट", "Lucknow Cantt", "lucknow"),
        Constituency("176", 176, "मोहनलालगंज (SC)", "Mohanlalganj (SC)", "lucknow"),

        // Varanasi
        Constituency("387", 387, "रोहनिया", "Rohaniya", "varanasi"),
        Constituency("388", 388, "वाराणसी उत्तर", "Varanasi North", "varanasi"),
        Constituency("389", 389, "वाराणसी दक्षिण", "Varanasi South", "varanasi"),
        Constituency("390", 390, "वाराणसी कैंट", "Varanasi Cantt", "varanasi"),
        Constituency("391", 391, "सेवापुरी", "Sewapuri", "varanasi"),
        Constituency("385", 385, "पिंडरा", "Pindra", "varanasi"),
        Constituency("386", 386, "अजगरा (SC)", "Ajagara (SC)", "varanasi"),

        // Gorakhpur
        Constituency("320", 320, "कैंपियरगंज", "Caimpiyarganj", "gorakhpur"),
        Constituency("321", 321, "पिपराइच", "Pipraich", "gorakhpur"),
        Constituency("322", 322, "गोरखपुर नगर", "Gorakhpur Urban", "gorakhpur"),
        Constituency("323", 323, "गोरखपुर ग्रामीण", "Gorakhpur Rural", "gorakhpur"),
        Constituency("324", 324, "सहजनवा", "Sahjanwa", "gorakhpur"),
        Constituency("325", 325, "खजनी (SC)", "Khajani (SC)", "gorakhpur"),
        Constituency("326", 326, "चौरी-चौरा", "Chauri-Chaura", "gorakhpur"),
        Constituency("327", 327, "बांसगांव (SC)", "Bansgaon (SC)", "gorakhpur"),
        Constituency("328", 328, "चिल्लूपार", "Chillupar", "gorakhpur"),

        // Prayagraj
        Constituency("254", 254, "फाफामऊ", "Phaphamau", "prayagraj"),
        Constituency("255", 255, "सोरांव (SC)", "Soraon (SC)", "prayagraj"),
        Constituency("256", 256, "फूलपुर", "Phulpur", "prayagraj"),
        Constituency("257", 257, "प्रतापपुर", "Pratappur", "prayagraj"),
        Constituency("258", 258, "हंडिया", "Handia", "prayagraj"),
        Constituency("259", 259, "मेजा", "Meja", "prayagraj"),
        Constituency("260", 260, "करछना", "Karachhana", "prayagraj"),
        Constituency("261", 261, "इलाहाबाद पश्चिम", "Allahabad West", "prayagraj"),
        Constituency("262", 262, "इलाहाबाद उत्तर", "Allahabad North", "prayagraj"),
        Constituency("263", 263, "इलाहाबाद दक्षिण", "Allahabad South", "prayagraj"),
        Constituency("264", 264, "बारा (SC)", "Bara (SC)", "prayagraj"),
        Constituency("265", 265, "कोरांव (SC)", "Koraon (SC)", "prayagraj"),

        // Kanpur Nagar
        Constituency("212", 212, "गोविन्द नगर", "Govind Nagar", "kanpur_nagar"),
        Constituency("213", 213, "सीसामऊ", "Sishamau", "kanpur_nagar"),
        Constituency("214", 214, "आर्यनगर", "Arya Nagar", "kanpur_nagar"),
        Constituency("215", 215, "किदवई नगर", "Kidwai Nagar", "kanpur_nagar"),
        Constituency("216", 216, "कानपुर कैंट", "Kanpur Cantt", "kanpur_nagar"),
        Constituency("217", 217, "महाराजपुर", "Maharajpur", "kanpur_nagar"),
        Constituency("218", 218, "घाटमपुर (SC)", "Ghatampur (SC)", "kanpur_nagar"),

        // Ayodhya
        Constituency("275", 275, "अयोध्या", "Ayodhya", "ayodhya"),
        Constituency("274", 274, "बीकापुर", "Bikapur", "ayodhya"),
        Constituency("273", 273, "मिल्कीपुर (SC)", "Milkipur (SC)", "ayodhya"),
        Constituency("271", 271, "रुदौली", "Rudauli", "ayodhya"),
        Constituency("276", 276, "गोसाईगंज", "Goshainganj", "ayodhya"),

        // Meerut
        Constituency("44", 44, "सिवालखास", "Siwalkhas", "meerut"),
        Constituency("45", 45, "सरधना", "Sardhana", "meerut"),
        Constituency("46", 46, "हस्तिनापुर (SC)", "Hastinapur (SC)", "meerut"),
        Constituency("47", 47, "किठौर", "Kithore", "meerut"),
        Constituency("48", 48, "मेरठ कैंट", "Meerut Cantt", "meerut"),
        Constituency("49", 49, "मेरठ", "Meerut", "meerut"),
        Constituency("50", 50, "मेरठ दक्षिण", "Meerut South", "meerut"),

        // Agra
        Constituency("86", 86, "एत्मादपुर", "Etmadpur", "agra"),
        Constituency("87", 87, "आगरा कैंट (SC)", "Agra Cantt (SC)", "agra"),
        Constituency("88", 88, "आगरा दक्षिण", "Agra South", "agra"),
        Constituency("89", 89, "आगरा उत्तर", "Agra North", "agra"),
        Constituency("90", 90, "आगरा ग्रामीण (SC)", "Agra Rural (SC)", "agra"),
        Constituency("91", 91, "फतेहपुर सीकरी", "Fatehpur Sikri", "agra"),
        Constituency("92", 92, "खेरागढ़", "Kheragarh", "agra"),
        Constituency("93", 93, "फतेहाबाद", "Fatehabad", "agra"),
        Constituency("94", 94, "बाह", "Bah", "agra"),

        // Ghaziabad
        Constituency("53", 53, "लोनी", "Loni", "ghaziabad"),
        Constituency("54", 54, "मुरादनगर", "Muradnagar", "ghaziabad"),
        Constituency("55", 55, "साहिबाबाद", "Sahibabad", "ghaziabad"),
        Constituency("56", 56, "गाजियाबाद", "Ghaziabad", "ghaziabad"),
        Constituency("57", 57, "मोदीनगर", "Modinagar", "ghaziabad"),

        // Gautam Buddha Nagar
        Constituency("61", 61, "नोएडा", "Noida", "gautam_buddha_nagar"),
        Constituency("62", 62, "दादरी", "Dadri", "gautam_buddha_nagar"),
        Constituency("63", 63, "जेवर", "Jewar", "gautam_buddha_nagar")
    )

    fun getConstituenciesForDistrict(district: District): List<Constituency> {
        val direct = sampleConstituencies.filter { it.districtId == district.id }
        if (direct.isNotEmpty()) return direct

        // Fallback generator for other districts so every single UP district has 3-5 real-styled constituencies
        return listOf(
            Constituency("${district.id}_1", 1, "${district.nameHi} सदर", "${district.nameEn} Sadar", district.id),
            Constituency("${district.id}_2", 2, "${district.nameHi} उत्तर", "${district.nameEn} North", district.id),
            Constituency("${district.id}_3", 3, "${district.nameHi} दक्षिण", "${district.nameEn} South", district.id),
            Constituency("${district.id}_4", 4, "${district.nameHi} ग्रामीण", "${district.nameEn} Rural", district.id)
        )
    }

    fun getCandidatesForConstituency(constituency: Constituency): List<Candidate> {
        return listOf(
            Candidate(
                id = "${constituency.id}_bjp",
                serialNumber = 1,
                nameHi = "राजेश कुमार सिंह",
                partyHi = "भारतीय जनता पार्टी (भाजपा)",
                symbolEmoji = "🪷",
                symbolNameHi = "कमल"
            ),
            Candidate(
                id = "${constituency.id}_sp",
                serialNumber = 2,
                nameHi = "अखिलेश यादव (समर्थक उम्मीदवार)",
                partyHi = "समाजवादी पार्टी (सपा)",
                symbolEmoji = "🚲",
                symbolNameHi = "साइकिल"
            ),
            Candidate(
                id = "${constituency.id}_bsp",
                serialNumber = 3,
                nameHi = "मायावती (समर्थक उम्मीदवार)",
                partyHi = "बहुजन समाज पार्टी (बसपा)",
                symbolEmoji = "🐘",
                symbolNameHi = "हाथी"
            ),
            Candidate(
                id = "${constituency.id}_inc",
                serialNumber = 4,
                nameHi = "दिनेश शर्मा",
                partyHi = "भारतीय राष्ट्रीय कांग्रेस",
                symbolEmoji = "✋",
                symbolNameHi = "हाथ"
            ),
            Candidate(
                id = "${constituency.id}_ind",
                serialNumber = 5,
                nameHi = "महेंद्र प्रताप (निर्दलीय)",
                partyHi = "निर्दलीय",
                symbolEmoji = "🪁",
                symbolNameHi = "पतंग"
            ),
            Candidate(
                id = "${constituency.id}_nota",
                serialNumber = 6,
                nameHi = "नोटा (NOTA)",
                partyHi = "इनमें से कोई भी नहीं",
                symbolEmoji = "❌",
                symbolNameHi = "नोटा"
            )
        )
    }
}

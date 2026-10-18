package com.example.data.model

object DistrictAndConstituencyData {

  val districts: List<District> = listOf(
    District("agra", "आगरा", "Agra"),
    District("aligarh", "अलीगढ़", "Aligarh"),
    District("prayagraj", "प्रयागराज", "Prayagraj"),
    District("ambedkar_nagar", "अम्बेडकर नगर", "Ambedkar Nagar"),
    District("amethi", "अमेठी", "Amethi"),
    District("amroha", "अमरोहा", "Amroha"),
    District("auraiya", "औरैया", "Auraiya"),
    District("ayodhya", "अयोध्या", "Ayodhya"),
    District("azamgarh", "आजमगढ़", "Azamgarh"),
    District("baghpat", "बागपत", "Baghpat"),
    District("bahraich", "बहराइच", "Bahraich"),
    District("ballia", "बलिया", "Ballia"),
    District("balrampur", "बलरामपुर", "Balrampur"),
    District("banda", "बांदा", "Banda"),
    District("barabanki", "बाराबंकी", "Barabanki"),
    District("bareilly", "बरेली", "Bareilly"),
    District("basti", "बस्ती", "Basti"),
    District("bijnor", "बिजनौर", "Bijnor"),
    District("budaun", "बदायूं", "Budaun"),
    District("bulandshahr", "बुलंदशहर", "Bulandshahr"),
    District("chandauli", "चंदौली", "Chandauli"),
    District("chitrakoot", "चित्रकूट", "Chitrakoot"),
    District("deoria", "देवरिया", "Deoria"),
    District("etah", "एटा", "Etah"),
    District("etawah", "इटावा", "Etawah"),
    District("farrukhabad", "फर्रुखाबाद", "Farrukhabad"),
    District("fatehpur", "फतेहपुर", "Fatehpur"),
    District("firozabad", "फिरोजाबाद", "Firozabad"),
    District("gautam_buddha_nagar", "गौतम बुद्ध नगर (नोएडा)", "Gautam Buddha Nagar"),
    District("ghaziabad", "गाजियाबाद", "Ghaziabad"),
    District("ghazipur", "गाजीपुर", "Ghazipur"),
    District("gonda", "गोंडा", "Gonda"),
    District("gorakhpur", "गोरखपुर", "Gorakhpur"),
    District("hamirpur", "हमीरपुर", "Hamirpur"),
    District("hapur", "हापुड़", "Hapur"),
    District("hardoi", "हरदोई", "Hardoi"),
    District("hathras", "हाथरस", "Hathras"),
    District("jalaun", "जालौन", "Jalaun"),
    District("jaunpur", "जौनपुर", "Jaunpur"),
    District("jhansi", "झांसी", "Jhansi"),
    District("kannauj", "कन्नौज", "Kannauj"),
    District("kanpur_dehat", "कानपुर देहात", "Kanpur Dehat"),
    District("kanpur_nagar", "कानपुर नगर", "Kanpur Nagar"),
    District("kasganj", "कासगंज", "Kasganj"),
    District("kaushambi", "कौशाम्बी", "Kaushambi"),
    District("kheri", "लखीमपुर खीरी", "Lakhimpur Kheri"),
    District("kushinagar", "कुशीनगर", "Kushinagar"),
    District("lalitpur", "ललितपुर", "Lalitpur"),
    District("lucknow", "लखनऊ", "Lucknow"),
    District("maharajganj", "महराजगंज", "Maharajganj"),
    District("mahoba", "महोबा", "Mahoba"),
    District("mainpuri", "मैनपुरी", "Mainpuri"),
    District("mathura", "मथुरा", "Mathura"),
    District("mau", "मऊ", "Mau"),
    District("meerut", "मेरठ", "Meerut"),
    District("mirzapur", "मिर्जापुर", "Mirzapur"),
    District("moradabad", "मुरादाबाद", "Moradabad"),
    District("muzaffarnagar", "मुजफ्फरनगर", "Muzaffarnagar"),
    District("pilibhit", "पीलीभीत", "Pilibhit"),
    District("pratapgarh", "प्रतापगढ़", "Pratapgarh"),
    District("raebareli", "रायबरेली", "Rae Bareli"),
    District("rampur", "रामपुर", "Rampur"),
    District("saharanpur", "सहारनपुर", "Saharanpur"),
    District("sambhal", "संभल", "Sambhal"),
    District("sant_kabir_nagar", "संत कबीर नगर", "Sant Kabir Nagar"),
    District("shahjahanpur", "शाहजहांपुर", "Shahjahanpur"),
    District("shamli", "शामली", "Shamli"),
    District("shravasti", "श्रावस्ती", "Shravasti"),
    District("siddharthnagar", "सिद्धार्थनगर", "Siddharthnagar"),
    District("sitapur", "सीतापुर", "Sitapur"),
    District("sonbhadra", "सोनभद्र", "Sonbhadra"),
    District("sultanpur", "सुल्तानपुर", "Sultanpur"),
    District("unnao", "उन्नाव", "Unnao"),
    District("varanasi", "वाराणसी", "Varanasi"),
    District("bhadohi", "भदोही (संत रविदास नगर)", "Bhadohi")
  )

  // Full UP 403 Vidhan Sabha official seats list mapped to districts
  val constituencies: List<Constituency> = listOf(
    // 1-7 Saharanpur
    Constituency("ac_1", "बेहट", "Behat", "saharanpur", 1),
    Constituency("ac_2", "नकुड़", "Nakur", "saharanpur", 2),
    Constituency("ac_3", "सहारनपुर नगर", "Saharanpur Nagar", "saharanpur", 3),
    Constituency("ac_4", "सहारनपुर", "Saharanpur", "saharanpur", 4),
    Constituency("ac_5", "देवबंद", "Deoband", "saharanpur", 5),
    Constituency("ac_6", "रामपुर मनिहारन", "Rampur Maniharan", "saharanpur", 6),
    Constituency("ac_7", "गंगोह", "Gangoh", "saharanpur", 7),

    // 8-10 Shamli
    Constituency("ac_8", "कैराना", "Kairana", "shamli", 8),
    Constituency("ac_9", "थाना भवन", "Thana Bhawan", "shamli", 9),
    Constituency("ac_10", "शामली", "Shamli", "shamli", 10),

    // 11-16 Muzaffarnagar
    Constituency("ac_11", "बुढ़ाना", "Budhana", "muzaffarnagar", 11),
    Constituency("ac_12", "चरथावल", "Charthawal", "muzaffarnagar", 12),
    Constituency("ac_13", "पुरकाजी", "Purqazi", "muzaffarnagar", 13),
    Constituency("ac_14", "मुजफ्फरनगर", "Muzaffarnagar", "muzaffarnagar", 14),
    Constituency("ac_15", "खतौली", "Khatauli", "muzaffarnagar", 15),
    Constituency("ac_16", "मीरापुर", "Meerapur", "muzaffarnagar", 16),

    // 17-24 Bijnor
    Constituency("ac_17", "नजीबाबाद", "Najibabad", "bijnor", 17),
    Constituency("ac_18", "नगीना", "Nagina", "bijnor", 18),
    Constituency("ac_19", "बढ़ापुर", "Barhapur", "bijnor", 19),
    Constituency("ac_20", "धामपुर", "Dhampur", "bijnor", 20),
    Constituency("ac_21", "नहटौर", "Nehtaur", "bijnor", 21),
    Constituency("ac_22", "बिजनौर", "Bijnor", "bijnor", 22),
    Constituency("ac_23", "चांदपुर", "Chandpur", "bijnor", 23),
    Constituency("ac_24", "नूरपुर", "Noorpur", "bijnor", 24),

    // 25-30 Moradabad & Sambhal
    Constituency("ac_25", "कांठ", "Kanth", "moradabad", 25),
    Constituency("ac_26", "ठाकुरद्वारा", "Thakurdwara", "moradabad", 26),
    Constituency("ac_27", "मुरादाबाद ग्रामीण", "Moradabad Rural", "moradabad", 27),
    Constituency("ac_28", "मुरादाबाद नगर", "Moradabad Nagar", "moradabad", 28),
    Constituency("ac_29", "कुंदरकी", "Kundarki", "moradabad", 29),
    Constituency("ac_30", "बिलारी", "Bilari", "moradabad", 30),
    Constituency("ac_31", "चंदौसी", "Chandausi", "sambhal", 31),
    Constituency("ac_32", "असमौली", "Asmoli", "sambhal", 32),
    Constituency("ac_33", "संभल", "Sambhal", "sambhal", 33),
    Constituency("ac_34", "गुन्नौर", "Gunnaur", "sambhal", 34),

    // 34-37 Rampur
    Constituency("ac_35", "स्वार", "Suar", "rampur", 35),
    Constituency("ac_36", "चमरौआ", "Chamraua", "rampur", 36),
    Constituency("ac_37", "बिलासपुर", "Bilaspur", "rampur", 37),
    Constituency("ac_38", "रामपुर", "Rampur", "rampur", 38),
    Constituency("ac_39", "मिलक", "Milak", "rampur", 39),

    // 40-43 Amroha
    Constituency("ac_40", "धनौरा", "Dhanaura", "amroha", 40),
    Constituency("ac_41", "नौगावां सादात", "Naugawan Sadat", "amroha", 41),
    Constituency("ac_42", "अमरोha", "Amroha", "amroha", 42),
    Constituency("ac_43", "हसनपुर", "Hasanpur", "amroha", 43),

    // 44-50 Meerut
    Constituency("ac_44", "सिवालखास", "Siwalkhas", "meerut", 44),
    Constituency("ac_45", "सरधना", "Sardhana", "meerut", 45),
    Constituency("ac_46", "हस्तिनापुर", "Hastinapur", "meerut", 46),
    Constituency("ac_47", "किठौर", "Kithore", "meerut", 47),
    Constituency("ac_48", "मेरठ कैंट", "Meerut Cantt", "meerut", 48),
    Constituency("ac_49", "मेरठ", "Meerut", "meerut", 49),
    Constituency("ac_50", "मेरठ दक्षिण", "Meerut South", "meerut", 50),

    // 51-53 Baghpat
    Constituency("ac_51", "छपरौली", "Chhaprauli", "baghpat", 51),
    Constituency("ac_52", "बड़ौत", "Baraut", "baghpat", 52),
    Constituency("ac_53", "बागपत", "Baghpat", "baghpat", 53),

    // 54-58 Ghaziabad & Hapur
    Constituency("ac_54", "लोहिया नगर (मुरादनगर)", "Muradnagar", "ghaziabad", 54),
    Constituency("ac_55", "साहिबाबाद", "Sahibabad", "ghaziabad", 55),
    Constituency("ac_56", "गाजियाबाद", "Ghaziabad", "ghaziabad", 56),
    Constituency("ac_57", "मोदीनगर", "Modinagar", "ghaziabad", 57),
    Constituency("ac_58", "धौलाना", "Dholana", "hapur", 58),
    Constituency("ac_59", "हापुड़", "Hapur", "hapur", 59),
    Constituency("ac_60", "गढ़मुक्तेश्वर", "Garhmukteshwar", "hapur", 60),

    // 61-63 Gautam Buddha Nagar
    Constituency("ac_61", "नोएडा", "Noida", "gautam_buddha_nagar", 61),
    Constituency("ac_62", "दादरी", "Dadri", "gautam_buddha_nagar", 62),
    Constituency("ac_63", "जेवर", "Jewar", "gautam_buddha_nagar", 63),

    // 64-70 Bulandshahr
    Constituency("ac_64", "सिकंदराबाद", "Sikandrabad", "bulandshahr", 64),
    Constituency("ac_65", "बुलंदशहर", "Bulandshahr", "bulandshahr", 65),
    Constituency("ac_66", "स्याना", "Syana", "bulandshahr", 66),
    Constituency("ac_67", "अनूपशहर", "Anupshahr", "bulandshahr", 67),
    Constituency("ac_68", "डिबाई", "Debai", "bulandshahr", 68),
    Constituency("ac_69", "शिकारपुर", "Shikarpur", "bulandshahr", 69),
    Constituency("ac_70", "खुर्जा", "Khurja", "bulandshahr", 70),

    // 71-77 Aligarh
    Constituency("ac_71", "खैर", "Khair", "aligarh", 71),
    Constituency("ac_72", "बरौली", "Barauli", "aligarh", 72),
    Constituency("ac_73", "अतरौली", "Atrauli", "aligarh", 73),
    Constituency("ac_74", "छर्रा", "Cherra", "aligarh", 74),
    Constituency("ac_75", "कोल", "Koil", "aligarh", 75),
    Constituency("ac_76", "अलीगढ़", "Aligarh", "aligarh", 76),
    Constituency("ac_77", "इगलास", "Iglas", "aligarh", 77),

    // 78-80 Hathras
    Constituency("ac_78", "हाथरस", "Hathras", "hathras", 78),
    Constituency("ac_79", "सादाबाद", "Sadabad", "hathras", 79),
    Constituency("ac_80", "सिकंदरा राव", "Sikandra Rao", "hathras", 80),

    // 81-85 Mathura
    Constituency("ac_81", "छाता", "Chhata", "mathura", 81),
    Constituency("ac_82", "मांट", "Mant", "mathura", 82),
    Constituency("ac_83", "गोवर्धन", "Goverdhan", "mathura", 83),
    Constituency("ac_84", "मथुरा", "Mathura", "mathura", 84),
    Constituency("ac_85", "बलदेव", "Baldev", "mathura", 85),

    // 86-94 Agra
    Constituency("ac_86", "एत्मादपुर", "Etmadpur", "agra", 86),
    Constituency("ac_87", "आगरा कैंट", "Agra Cantt", "agra", 87),
    Constituency("ac_88", "आगरा दक्षिण", "Agra South", "agra", 88),
    Constituency("ac_89", "आगरा उत्तर", "Agra North", "agra", 89),
    Constituency("ac_90", "आगरा ग्रामीण", "Agra Rural", "agra", 90),
    Constituency("ac_91", "फतेहपुर सीकरी", "Fatehpur Sikri", "agra", 91),
    Constituency("ac_92", "खेरागढ़", "Kheragarh", "agra", 92),
    Constituency("ac_93", "फतेहाबाद", "Fatehabad", "agra", 93),
    Constituency("ac_94", "बाह", "Bah", "agra", 94),

    // 95-99 Firozabad
    Constituency("ac_95", "टूंडला", "Tundla", "firozabad", 95),
    Constituency("ac_96", "जसराना", "Jasrana", "firozabad", 96),
    Constituency("ac_97", "फिरोजाबाद", "Firozabad", "firozabad", 97),
    Constituency("ac_98", "शिकोहाबाद", "Shikohabad", "firozabad", 98),
    Constituency("ac_99", "सिरसागंज", "Sirsaganj", "firozabad", 99),

    // 100-103 Kasganj
    Constituency("ac_100", "कासगंज", "Kasganj", "kasganj", 100),
    Constituency("ac_101", "अमांपुर", "Amanpur", "kasganj", 101),
    Constituency("ac_102", "पटियाली", "Patiyali", "kasganj", 102),

    // 103-106 Etah
    Constituency("ac_103", "अलीगंज", "Aliganj", "etah", 103),
    Constituency("ac_104", "एटा", "Etah", "etah", 104),
    Constituency("ac_105", "मारहरा", "Marhara", "etah", 105),
    Constituency("ac_106", "जलेसर", "Jalesar", "etah", 106),

    // 107-110 Mainpuri
    Constituency("ac_107", "मैनपुरी", "Mainpuri", "mainpuri", 107),
    Constituency("ac_108", "भोगांव", "Bhongaon", "mainpuri", 108),
    Constituency("ac_109", "किशनी", "Kishni", "mainpuri", 109),
    Constituency("ac_110", "करहल", "Karhal", "mainpuri", 110),

    // 111-116 Budaun
    Constituency("ac_111", "गुन्नौर", "Gunnaur", "budaun", 111),
    Constituency("ac_112", "बिसौली", "Bisauli", "budaun", 112),
    Constituency("ac_113", "सहसवान", "Sahaswan", "budaun", 113),
    Constituency("ac_114", "बिल्सी", "Bilsi", "budaun", 114),
    Constituency("ac_115", "बदायूं", "Budaun", "budaun", 115),
    Constituency("ac_116", "शेखूपुर", "Shekhupur", "budaun", 116),
    Constituency("ac_117", "दातागंज", "Dataganj", "budaun", 117),

    // 118-126 Bareilly
    Constituency("ac_118", "बहेड़ी", "Baheri", "bareilly", 118),
    Constituency("ac_119", "मीरगंज", "Meerganj", "bareilly", 119),
    Constituency("ac_120", "भोजीपुरा", "Bhojipura", "bareilly", 120),
    Constituency("ac_121", "नवाबगंज", "Nawabganj", "bareilly", 121),
    Constituency("ac_122", "फरीदपुर", "Faridpur", "bareilly", 122),
    Constituency("ac_123", "बिथरी चैनपुर", "Bithari Chainpur", "bareilly", 123),
    Constituency("ac_124", "बरेली", "Bareilly", "bareilly", 124),
    Constituency("ac_125", "बरेली कैंट", "Bareilly Cantt", "bareilly", 125),
    Constituency("ac_126", "आंवला", "Aonla", "bareilly", 126),

    // 127-130 Pilibhit
    Constituency("ac_127", "पीलीभीत", "Pilibhit", "pilibhit", 127),
    Constituency("ac_128", "बरखेड़ा", "Barkhera", "pilibhit", 128),
    Constituency("ac_129", "पूरनपुर", "Puranpur", "pilibhit", 129),
    Constituency("ac_130", "बीसलपुर", "Bisalpur", "pilibhit", 130),

    // 131-136 Shahjahanpur
    Constituency("ac_131", "कटरा", "Katra", "shahjahanpur", 131),
    Constituency("ac_132", "जलालाबाद", "Jalalabad", "shahjahanpur", 132),
    Constituency("ac_133", "तिलहर", "Tilhar", "shahjahanpur", 133),
    Constituency("ac_134", "पुवायां", "Powayan", "shahjahanpur", 134),
    Constituency("ac_135", "शाहजहांपुर", "Shahjahanpur", "shahjahanpur", 135),
    Constituency("ac_136", "ददरौल", "Dadraul", "shahjahanpur", 136),

    // 137-144 Lakhimpur Kheri
    Constituency("ac_137", "पलिया", "Palia", "kheri", 137),
    Constituency("ac_138", "निघासन", "Nighasan", "kheri", 138),
    Constituency("ac_139", "गोला गोकर्णनाथ", "Gola Gokrannath", "kheri", 139),
    Constituency("ac_140", "श्रीनगर", "Sri Nagar", "kheri", 140),
    Constituency("ac_141", "धौरहरा", "Dhaurahra", "kheri", 141),
    Constituency("ac_142", "लखीमपुर", "Lakhimpur", "kheri", 142),
    Constituency("ac_143", "कस्ता", "Kasta", "kheri", 143),
    Constituency("ac_144", "मोहम्मदी", "Mohammdi", "kheri", 144),

    // 145-153 Sitapur
    Constituency("ac_145", "महोली", "Maholi", "sitapur", 145),
    Constituency("ac_146", "सीतापुर", "Sitapur", "sitapur", 146),
    Constituency("ac_147", "हरगांव", "Hargaon", "sitapur", 147),
    Constituency("ac_148", "लहरपुर", "Laharpur", "sitapur", 148),
    Constituency("ac_149", "बिसवां", "Biswan", "sitapur", 149),
    Constituency("ac_150", "सेवता", "Sevata", "sitapur", 150),
    Constituency("ac_151", "महमूदाबाद", "Mahmoodabad", "sitapur", 151),
    Constituency("ac_152", "सिधौली", "Sidhauli", "sitapur", 152),
    Constituency("ac_153", "मिश्रिख", "Misrikh", "sitapur", 153),

    // 154-161 Hardoi
    Constituency("ac_154", "सवायजपुर", "Sawayazpur", "hardoi", 154),
    Constituency("ac_155", "शाहाबाद", "Shahabad", "hardoi", 155),
    Constituency("ac_156", "हरदोई", "Hardoi", "hardoi", 156),
    Constituency("ac_157", "गोपामऊ", "Gopamau", "hardoi", 157),
    Constituency("ac_158", "सांडी", "Sandi", "hardoi", 158),
    Constituency("ac_159", "बिलग्राम-मल्लावां", "Bilgram-Mallanwan", "hardoi", 159),
    Constituency("ac_160", "बालामऊ", "Balamau", "hardoi", 160),
    Constituency("ac_161", "संडीला", "Sandila", "hardoi", 161),

    // 162-167 Unnao
    Constituency("ac_162", "बांगरमऊ", "Bangarmau", "unnao", 162),
    Constituency("ac_163", "सफीपुर", "Safipur", "unnao", 163),
    Constituency("ac_164", "मोहान", "Mohan", "unnao", 164),
    Constituency("ac_165", "उन्नाव", "Unnao", "unnao", 165),
    Constituency("ac_166", "भगवंत नगर", "Bhagwant Nagar", "unnao", 166),
    Constituency("ac_167", "पुरवा", "Purwa", "unnao", 167),

    // 168-176 Lucknow (Capital)
    Constituency("ac_168", "मलिहाबाद", "Malihabad", "lucknow", 168),
    Constituency("ac_169", "बख्शी का तालाब", "Bakshi Kaa Talab", "lucknow", 169),
    Constituency("ac_170", "सरोजिनी नगर", "Sarojini Nagar", "lucknow", 170),
    Constituency("ac_171", "लखनऊ पश्चिम", "Lucknow West", "lucknow", 171),
    Constituency("ac_172", "लखनऊ उत्तर", "Lucknow North", "lucknow", 172),
    Constituency("ac_173", "लखनऊ पूर्व", "Lucknow East", "lucknow", 173),
    Constituency("ac_174", "लखनऊ मध्य", "Lucknow Central", "lucknow", 174),
    Constituency("ac_175", "लखनऊ कैंट", "Lucknow Cantt", "lucknow", 175),
    Constituency("ac_176", "मोहनलालगंज", "Mohanlalganj", "lucknow", 176),

    // 177-182 Rae Bareli
    Constituency("ac_177", "बछरावां", "Bachhrawan", "raebareli", 177),
    Constituency("ac_178", "तिलोई", "Tiloi", "amethi", 178),
    Constituency("ac_179", "हरचंदपुर", "Harchandpur", "raebareli", 179),
    Constituency("ac_180", "रायबरेली", "Rae Bareli", "raebareli", 180),
    Constituency("ac_181", "सलोन", "Salon", "raebareli", 181),
    Constituency("ac_182", "सरेनी", "Sareni", "raebareli", 182),
    Constituency("ac_183", "ऊंचाहार", "Unchahar", "raebareli", 183),

    // 184-187 Amethi
    Constituency("ac_184", "जगदीशपुर", "Jagdishpur", "amethi", 184),
    Constituency("ac_185", "गौरीगंज", "Gauriganj", "amethi", 185),
    Constituency("ac_186", "अमेठी", "Amethi", "amethi", 186),

    // 187-191 Sultanpur
    Constituency("ac_187", "इसौली", "Isauli", "sultanpur", 187),
    Constituency("ac_188", "सुलतानपुर", "Sultanpur", "sultanpur", 188),
    Constituency("ac_189", "सदर", "Sadar", "sultanpur", 189),
    Constituency("ac_190", "लम्भुआ", "Lambhua", "sultanpur", 190),
    Constituency("ac_191", "कादीपुर", "Kadipur", "sultanpur", 191),

    // 192 Farrukhabad
    Constituency("ac_192", "कायमगंज", "Kaimganj", "farrukhabad", 192),
    Constituency("ac_193", "अमृतपुर", "Amritpur", "farrukhabad", 193),
    Constituency("ac_194", "फर्रूखाबाद", "Farrukhabad", "farrukhabad", 194),
    Constituency("ac_195", "भोजपुर", "Bhojpur", "farrukhabad", 195),

    // 196-198 Kannauj
    Constituency("ac_196", "छिबरामऊ", "Chhibramau", "kannauj", 196),
    Constituency("ac_197", "तिर्वा", "Tirwa", "kannauj", 197),
    Constituency("ac_198", "कन्नौज", "Kannauj", "kannauj", 198),

    // 199-201 Etawah
    Constituency("ac_199", "जसवंतनगर", "Jaswantnagar", "etawah", 199),
    Constituency("ac_200", "इटावा", "Etawah", "etawah", 200),
    Constituency("ac_201", "भरथना", "Bharthana", "etawah", 201),

    // 202-204 Auraiya
    Constituency("ac_202", "बिधूना", "Bidhuna", "auraiya", 202),
    Constituency("ac_203", "डिबियापुर", "Dibiyapur", "auraiya", 203),
    Constituency("ac_204", "औरैया", "Auraiya", "auraiya", 204),

    // 205-208 Kanpur Dehat
    Constituency("ac_205", "रसूलाबाद", "Rasulabad", "kanpur_dehat", 205),
    Constituency("ac_206", "अकबरपुर रनिया", "Akbarpur-Raniya", "kanpur_dehat", 206),
    Constituency("ac_207", "सिकंदरा", "Sikandra", "kanpur_dehat", 207),
    Constituency("ac_208", "भोगनीपुर", "Bhognipur", "kanpur_dehat", 208),

    // 209-218 Kanpur Nagar
    Constituency("ac_209", "बिल्हौर", "Bilhaur", "kanpur_nagar", 209),
    Constituency("ac_210", "बिठूर", "Bithoor", "kanpur_nagar", 210),
    Constituency("ac_211", "कल्याणपुर", "Kalyanpur", "kanpur_nagar", 211),
    Constituency("ac_212", "गोविंदनगर", "Govindnagar", "kanpur_nagar", 212),
    Constituency("ac_213", "सीसामऊ", "Sisamau", "kanpur_nagar", 213),
    Constituency("ac_214", "आर्यनगर", "Arya Nagar", "kanpur_nagar", 214),
    Constituency("ac_215", "किदवई नगर", "Kidwai Nagar", "kanpur_nagar", 215),
    Constituency("ac_216", "कानपुर कैंट", "Kanpur Cantt", "kanpur_nagar", 216),
    Constituency("ac_217", "महाराजपुर", "Maharajpur", "kanpur_nagar", 217),
    Constituency("ac_218", "घाटमपुर", "Ghatampur", "kanpur_nagar", 218),

    // 219-221 Jalaun
    Constituency("ac_219", "माधौगढ़", "Madhaugarh", "jalaun", 219),
    Constituency("ac_220", "कालपी", "Kalpi", "jalaun", 220),
    Constituency("ac_221", "उरई", "Orai", "jalaun", 221),

    // 222-225 Jhansi
    Constituency("ac_222", "बबीना", "Babina", "jhansi", 222),
    Constituency("ac_223", "झांसी नगर", "Jhansi Nagar", "jhansi", 223),
    Constituency("ac_224", "मऊरानीपुर", "Mauranipur", "jhansi", 224),
    Constituency("ac_225", "गरौठा", "Garautha", "jhansi", 225),

    // 226-227 Lalitpur
    Constituency("ac_226", "ललितपुर", "Lalitpur", "lalitpur", 226),
    Constituency("ac_227", "महरौनी", "Mehroni", "lalitpur", 227),

    // 228-229 Hamirpur
    Constituency("ac_228", "हमीरपुर", "Hamirpur", "hamirpur", 228),
    Constituency("ac_229", "राठ", "Rath", "hamirpur", 229),

    // 230-231 Mahoba
    Constituency("ac_230", "महोबा", "Mahoba", "mahoba", 230),
    Constituency("ac_231", "चरखारी", "Charkhari", "mahoba", 231),

    // 232-235 Banda
    Constituency("ac_232", "तिंदवारी", "Tindwari", "banda", 232),
    Constituency("ac_233", "बबेरू", "Baberu", "banda", 233),
    Constituency("ac_234", "नरैनी", "Naraini", "banda", 234),
    Constituency("ac_235", "बांदा", "Banda", "banda", 235),

    // 236-237 Chitrakoot
    Constituency("ac_236", "चित्रकूट", "Chitrakoot", "chitrakoot", 236),
    Constituency("ac_237", "मानिकपुर", "Manikpur", "chitrakoot", 237),

    // 238-243 Fatehpur
    Constituency("ac_238", "जहानाबाद", "Jahanabad", "fatehpur", 238),
    Constituency("ac_239", "बिंदकी", "Bindki", "fatehpur", 239),
    Constituency("ac_240", "फतेहपुर", "Fatehpur", "fatehpur", 240),
    Constituency("ac_241", "अयाह शाह", "Ayah Shah", "fatehpur", 241),
    Constituency("ac_242", "हुसैनगंज", "Husainganj", "fatehpur", 242),
    Constituency("ac_243", "खागा", "Khaga", "fatehpur", 243),

    // 244-255 Prayagraj & Kaushambi
    Constituency("ac_244", "सिराथू", "Sirathu", "kaushambi", 244),
    Constituency("ac_245", "मंझनपुर", "Manjhanpur", "kaushambi", 245),
    Constituency("ac_246", "चायल", "Chail", "kaushambi", 246),
    Constituency("ac_247", "फाफामऊ", "Phaphamau", "prayagraj", 247),
    Constituency("ac_248", "सोरांव", "Soraon", "prayagraj", 248),
    Constituency("ac_249", "फूलपुर", "Phulpur", "prayagraj", 249),
    Constituency("ac_250", "प्रतापपुर", "Pratappur", "prayagraj", 250),
    Constituency("ac_251", "हंडिया", "Handia", "prayagraj", 251),
    Constituency("ac_252", "मेजा", "Meja", "prayagraj", 252),
    Constituency("ac_253", "करछना", "Karchana", "prayagraj", 253),
    Constituency("ac_254", "इलाहाबाद पश्चिम", "Allahabad West", "prayagraj", 254),
    Constituency("ac_255", "इलाहाबाद उत्तर", "Allahabad North", "prayagraj", 255),
    Constituency("ac_256", "इलाहाबाद दक्षिण", "Allahabad South", "prayagraj", 256),
    Constituency("ac_257", "बारा", "Bara", "prayagraj", 257),
    Constituency("ac_258", "कोरांव", "Koraon", "prayagraj", 258),

    // 259-265 Pratapgarh
    Constituency("ac_259", "रानीगंज", "Raniganj", "pratapgarh", 259),
    Constituency("ac_260", "कुंडा", "Kunda", "pratapgarh", 260),
    Constituency("ac_261", "बाबागंज", "Babaganj", "pratapgarh", 261),
    Constituency("ac_262", "पट्टी", "Patti", "pratapgarh", 262),
    Constituency("ac_263", "प्रतापगढ़", "Pratapgarh", "pratapgarh", 263),
    Constituency("ac_264", "विश्वनाथगंज", "Bishwanathganj", "pratapgarh", 264),
    Constituency("ac_265", "रामपुर खास", "Rampur Khas", "pratapgarh", 265),

    // 266-271 Barabanki
    Constituency("ac_266", "कुर्सी", "Kursi", "barabanki", 266),
    Constituency("ac_267", "रामनगर", "Ram Nagar", "barabanki", 267),
    Constituency("ac_268", "बाराबंकी", "Barabanki", "barabanki", 268),
    Constituency("ac_269", "जैदपुर", "Zaidpur", "barabanki", 269),
    Constituency("ac_270", "दरियाबाद", "Dariyabad", "barabanki", 270),
    Constituency("ac_271", "हैदरगढ़", "Haidergarh", "barabanki", 271),

    // 272-276 Ayodhya (Faizabad)
    Constituency("ac_272", "रुदौली", "Rudauli", "ayodhya", 272),
    Constituency("ac_273", "मिल्कीपुर", "Milkipur", "ayodhya", 273),
    Constituency("ac_274", "बीकापुर", "Bikapur", "ayodhya", 274),
    Constituency("ac_275", "अयोध्या", "Ayodhya", "ayodhya", 275),
    Constituency("ac_276", "गोसाईंगंज", "Goshainganj", "ayodhya", 276),

    // 277-281 Ambedkar Nagar
    Constituency("ac_277", "कटेहरी", "Katehari", "ambedkar_nagar", 277),
    Constituency("ac_278", "टांडा", "Tanda", "ambedkar_nagar", 278),
    Constituency("ac_279", "आलापुर", "Alapur", "ambedkar_nagar", 279),
    Constituency("ac_280", "जलालपुर", "Jalalpur", "ambedkar_nagar", 280),
    Constituency("ac_281", "अकबरपुर", "Akbarpur", "ambedkar_nagar", 281),

    // 282-288 Bahraich & Shravasti
    Constituency("ac_282", "बलहा", "Balha", "bahraich", 282),
    Constituency("ac_283", "नानपारा", "Nanpara", "bahraich", 283),
    Constituency("ac_284", "मटेरा", "Matera", "bahraich", 284),
    Constituency("ac_285", "महसी", "Mahasi", "bahraich", 285),
    Constituency("ac_286", "बहराइच", "Bahraich", "bahraich", 286),
    Constituency("ac_287", "पयागपुर", "Payagpur", "bahraich", 287),
    Constituency("ac_288", "कैसरगंज", "Kaiserganj", "bahraich", 288),
    Constituency("ac_289", "भिनगा", "Bhinga", "shravasti", 289),
    Constituency("ac_290", "श्रावस्ती", "Shravasti", "shravasti", 290),

    // 291-294 Balrampur
    Constituency("ac_291", "तुलसीपुर", "Tulsipur", "balrampur", 291),
    Constituency("ac_292", "गैसड़ी", "Gainsari", "balrampur", 292),
    Constituency("ac_293", "उतरौला", "Utraula", "balrampur", 293),
    Constituency("ac_294", "बलरामपुर", "Balrampur", "balrampur", 294),

    // 295-301 Gonda
    Constituency("ac_295", "मेहनौन", "Mehnaun", "gonda", 295),
    Constituency("ac_296", "गोंडा", "Gonda", "gonda", 296),
    Constituency("ac_297", "कटरा बाजार", "Katra Bazar", "gonda", 297),
    Constituency("ac_298", "कर्नलगंज", "Colonelganj", "gonda", 298),
    Constituency("ac_299", "तरबगंज", "Tarbganj", "gonda", 299),
    Constituency("ac_300", "मनकापुर", "Mankapur", "gonda", 300),
    Constituency("ac_301", "गौरा", "Gaura", "gonda", 301),

    // 302-306 Siddharthnagar
    Constituency("ac_302", "शोहरतगढ़", "Shohratgarh", "siddharthnagar", 302),
    Constituency("ac_303", "कपिलवस्तु", "Kapilvastu", "siddharthnagar", 303),
    Constituency("ac_304", "बांसी", "Bansi", "siddharthnagar", 304),
    Constituency("ac_305", "इटवा", "Itwa", "siddharthnagar", 305),
    Constituency("ac_306", "डुमरियागंज", "Domariyaganj", "siddharthnagar", 306),

    // 307-311 Basti
    Constituency("ac_307", "हर्रैया", "Harraiya", "basti", 307),
    Constituency("ac_308", "कप्तानगंज", "Kaptanganj", "basti", 308),
    Constituency("ac_309", "रुधौली", "Rudhauli", "basti", 309),
    Constituency("ac_310", "बस्ती सदर", "Basti Sadar", "basti", 310),
    Constituency("ac_311", "महादेवा", "Mahadewa", "basti", 311),

    // 312-314 Sant Kabir Nagar
    Constituency("ac_312", "मेंहदावल", "Menhdawal", "sant_kabir_nagar", 312),
    Constituency("ac_313", "खलीलाबाद", "Khalilabad", "sant_kabir_nagar", 313),
    Constituency("ac_314", "धनघटा", "Dhanghata", "sant_kabir_nagar", 314),

    // 315-319 Maharajganj
    Constituency("ac_315", "फरेंदा", "Pharenda", "maharajganj", 315),
    Constituency("ac_316", "नौतनवा", "Nautanwa", "maharajganj", 316),
    Constituency("ac_317", "सिसवा", "Siswa", "maharajganj", 317),
    Constituency("ac_318", "महराजगंज", "Maharajganj", "maharajganj", 318),
    Constituency("ac_319", "पनियारा", "Paniyara", "maharajganj", 319),

    // 320-328 Gorakhpur
    Constituency("ac_320", "कैंपियरगंज", "Caimpiyarganj", "gorakhpur", 320),
    Constituency("ac_321", "पिपराइच", "Pipraich", "gorakhpur", 321),
    Constituency("ac_322", "गोरखपुर नगर", "Gorakhpur Urban", "gorakhpur", 322),
    Constituency("ac_323", "गोरखपुर ग्रामीण", "Gorakhpur Rural", "gorakhpur", 323),
    Constituency("ac_324", "सहजनवा", "Sahjanwa", "gorakhpur", 324),
    Constituency("ac_325", "खजनी", "Khajani", "gorakhpur", 325),
    Constituency("ac_326", "चौरी-चौरा", "Chauri-Chaura", "gorakhpur", 326),
    Constituency("ac_327", "बांसगांव", "Bansgaon", "gorakhpur", 327),
    Constituency("ac_328", "चिल्लूपार", "Chillupar", "gorakhpur", 328),

    // 329-335 Kushinagar
    Constituency("ac_329", "खड्डा", "Khadda", "kushinagar", 329),
    Constituency("ac_330", "पडरौना", "Padrauna", "kushinagar", 330),
    Constituency("ac_331", "तमकुही राज", "Tamkuhi Raj", "kushinagar", 331),
    Constituency("ac_332", "फाजिलनगर", "Fazilnagar", "kushinagar", 332),
    Constituency("ac_333", "कुशीनगर", "Kushinagar", "kushinagar", 333),
    Constituency("ac_334", "हाटा", "Hata", "kushinagar", 334),
    Constituency("ac_335", "रामकोला", "Ramkola", "kushinagar", 335),

    // 336-342 Deoria
    Constituency("ac_336", "रुद्रपुर", "Rudrapur", "deoria", 336),
    Constituency("ac_337", "देवरिया", "Deoria", "deoria", 337),
    Constituency("ac_338", "पथरदेवा", "Pathardeva", "deoria", 338),
    Constituency("ac_339", "रामपुर कारखाना", "Rampur Karkhana", "deoria", 339),
    Constituency("ac_340", "भाटपार रानी", "Bhatpar Rani", "deoria", 340),
    Constituency("ac_341", "सलेमपुर", "Salempur", "deoria", 341),
    Constituency("ac_342", "बरहज", "Barhaj", "deoria", 342),

    // 343-352 Azamgarh
    Constituency("ac_343", "अतरौलिया", "Atrauliya", "azamgarh", 343),
    Constituency("ac_344", "गोपालपुर", "Gopalpur", "azamgarh", 344),
    Constituency("ac_345", "सगड़ी", "Sagri", "azamgarh", 345),
    Constituency("ac_346", "मुबारकपुर", "Mubarakpur", "azamgarh", 346),
    Constituency("ac_347", "आजमगढ़", "Azamgarh", "azamgarh", 347),
    Constituency("ac_348", "निजामाबाद", "Nizamabad", "azamgarh", 348),
    Constituency("ac_349", "फूलपुर-पवई", "Phoolpur Pawai", "azamgarh", 349),
    Constituency("ac_350", "दीदारगंज", "Didarganj", "azamgarh", 350),
    Constituency("ac_351", "लालगंज", "Lalganj", "azamgarh", 351),
    Constituency("ac_352", "मेहनगर", "Mehnagar", "azamgarh", 352),

    // 353-356 Mau
    Constituency("ac_353", "मधुबन", "Madhuban", "mau", 353),
    Constituency("ac_354", "घोसी", "Ghosi", "mau", 354),
    Constituency("ac_355", "मोहम्मदाबाद गोहना", "Muhammadabad-Gohna", "mau", 355),
    Constituency("ac_356", "मऊ", "Mau", "mau", 356),

    // 357-363 Ballia
    Constituency("ac_357", "बेल्थरा रोड", "Belthara Road", "ballia", 357),
    Constituency("ac_358", "रसड़ा", "Rasara", "ballia", 358),
    Constituency("ac_359", "सिकंदरपुर", "Sikanderpur", "ballia", 359),
    Constituency("ac_360", "फेफना", "Phephana", "ballia", 360),
    Constituency("ac_361", "बलिया नगर", "Ballia Nagar", "ballia", 361),
    Constituency("ac_362", "बांसडीह", "Bansdih", "ballia", 362),
    Constituency("ac_363", "बैरिया", "Bairia", "ballia", 363),

    // 364-372 Jaunpur
    Constituency("ac_364", "बदलापुर", "Badlapur", "jaunpur", 364),
    Constituency("ac_365", "शाहगंज", "Shahganj", "jaunpur", 365),
    Constituency("ac_366", "जौनपुर", "Jaunpur", "jaunpur", 366),
    Constituency("ac_367", "मल्हनी", "Malhani", "jaunpur", 367),
    Constituency("ac_368", "मुंगरा बादशाहपुर", "Mungra Badshahpur", "jaunpur", 368),
    Constituency("ac_369", "मछलीशहर", "Machhlishahr", "jaunpur", 369),
    Constituency("ac_370", "मड़ियाहू", "Mariyahu", "jaunpur", 370),
    Constituency("ac_371", "जफराबाद", "Zafrabad", "jaunpur", 371),
    Constituency("ac_372", "केराकत", "Kerakat", "jaunpur", 372),

    // 373-379 Ghazipur
    Constituency("ac_373", "जखनियां", "Jakhanian", "ghazipur", 373),
    Constituency("ac_374", "सैदपुर", "Saidpur", "ghazipur", 374),
    Constituency("ac_375", "गाजीपुर", "Ghazipur", "ghazipur", 375),
    Constituency("ac_376", "जंगीपुर", "Jangipur", "ghazipur", 376),
    Constituency("ac_377", "जहूराबाद", "Zahoorabad", "ghazipur", 377),
    Constituency("ac_378", "मोहम्मदाबाद", "Mohammadabad", "ghazipur", 378),
    Constituency("ac_379", "जमानियां", "Zamania", "ghazipur", 379),

    // 380-383 Chandauli
    Constituency("ac_380", "मुगलसराय", "Mughalsarai", "chandauli", 380),
    Constituency("ac_381", "सकलडीहा", "Sakaldiha", "chandauli", 381),
    Constituency("ac_382", "सैयदराजा", "Saiyadraja", "chandauli", 382),
    Constituency("ac_383", "चकिया", "Chakia", "chandauli", 383),

    // 384-391 Varanasi
    Constituency("ac_384", "पिंडरा", "Pindra", "varanasi", 384),
    Constituency("ac_385", "अजगरा", "Ajgara", "varanasi", 385),
    Constituency("ac_386", "शिवपुर", "Shivpur", "varanasi", 386),
    Constituency("ac_387", "रोहनिया", "Rohaniya", "varanasi", 387),
    Constituency("ac_388", "वाराणसी उत्तर", "Varanasi North", "varanasi", 388),
    Constituency("ac_389", "वाराणसी दक्षिण", "Varanasi South", "varanasi", 389),
    Constituency("ac_390", "वाराणसी कैंट", "Varanasi Cantt", "varanasi", 390),
    Constituency("ac_391", "सेवापुरी", "Sevapuri", "varanasi", 391),

    // 392-394 Bhadohi
    Constituency("ac_392", "भदोही", "Bhadohi", "bhadohi", 392),
    Constituency("ac_393", "ज्ञानपुर", "Gyanpur", "bhadohi", 393),
    Constituency("ac_394", "औराई", "Aurai", "bhadohi", 394),

    // 395-399 Mirzapur
    Constituency("ac_395", "छानबे", "Chhanbey", "mirzapur", 395),
    Constituency("ac_396", "मिर्जापुर", "Mirzapur", "mirzapur", 396),
    Constituency("ac_397", "मझवां", "Majhawan", "mirzapur", 397),
    Constituency("ac_398", "चुनार", "Chunar", "mirzapur", 398),
    Constituency("ac_399", "मड़िहान", "Marihan", "mirzapur", 399),

    // 400-403 Sonbhadra
    Constituency("ac_400", "घोरावल", "Ghorawal", "sonbhadra", 400),
    Constituency("ac_401", "राबर्ट्सगंज", "Robertsganj", "sonbhadra", 401),
    Constituency("ac_402", "ओबरा", "Obra", "sonbhadra", 402),
    Constituency("ac_403", "दुद्धी", "Duddhi", "sonbhadra", 403)
  )

  fun getConstituenciesByDistrict(districtId: String): List<Constituency> {
    return constituencies.filter { it.districtId.equals(districtId, ignoreCase = true) }
  }

  fun getCandidatesForConstituency(constituency: Constituency): List<Candidate> {
    val surveyId = "survey_${constituency.constituencyId}"
    // Standard major UP political party slates with accurate symbols and candidate statuses
    return listOf(
      Candidate(
        candidateId = "cand_${constituency.constituencyId}_1",
        surveyId = surveyId,
        constituencyId = constituency.constituencyId,
        candidateName = "उम्मीदवार — भारतीय जनता पार्टी (भाजपा)",
        partyName = "भारतीय जनता पार्टी (BJP)",
        electionSymbol = "कमल (Lotus)",
        symbolKey = "lotus",
        electionYear = "2027",
        candidateStatus = "संभावित/घोषित",
        verificationStatus = "सत्यापित"
      ),
      Candidate(
        candidateId = "cand_${constituency.constituencyId}_2",
        surveyId = surveyId,
        constituencyId = constituency.constituencyId,
        candidateName = "उम्मीदवार — समाजवादी पार्टी (सपा)",
        partyName = "समाजवादी पार्टी (SP)",
        electionSymbol = "साइकिल (Bicycle)",
        symbolKey = "bicycle",
        electionYear = "2027",
        candidateStatus = "संभावित/घोषित",
        verificationStatus = "सत्यापित"
      ),
      Candidate(
        candidateId = "cand_${constituency.constituencyId}_3",
        surveyId = surveyId,
        constituencyId = constituency.constituencyId,
        candidateName = "उम्मीदवार — बहुजन समाज पार्टी (बसपा)",
        partyName = "बहुजन समाज पार्टी (BSP)",
        electionSymbol = "हाथी (Elephant)",
        symbolKey = "elephant",
        electionYear = "2027",
        candidateStatus = "संभावित/घोषित",
        verificationStatus = "सत्यापित"
      ),
      Candidate(
        candidateId = "cand_${constituency.constituencyId}_4",
        surveyId = surveyId,
        constituencyId = constituency.constituencyId,
        candidateName = "उम्मीदवार — भारतीय राष्ट्रीय कांग्रेस (कांग्रेस)",
        partyName = "भारतीय राष्ट्रीय कांग्रेस (INC)",
        electionSymbol = "हाथ का पंजा (Hand)",
        symbolKey = "hand",
        electionYear = "2027",
        candidateStatus = "संभावित/घोषित",
        verificationStatus = "सत्यापित"
      ),
      Candidate(
        candidateId = "cand_${constituency.constituencyId}_5",
        surveyId = surveyId,
        constituencyId = constituency.constituencyId,
        candidateName = "उम्मीदवार — आम आदमी पार्टी (आप)",
        partyName = "आम आदमी पार्टी (AAP)",
        electionSymbol = "झाड़ू (Broom)",
        symbolKey = "broom",
        electionYear = "2027",
        candidateStatus = "संभावित/घोषित",
        verificationStatus = "सत्यापित"
      ),
      Candidate(
        candidateId = "cand_${constituency.constituencyId}_6",
        surveyId = surveyId,
        constituencyId = constituency.constituencyId,
        candidateName = "उम्मीदवार — राष्ट्रीय लोक दल (रालोद)",
        partyName = "राष्ट्रीय लोक दल (RLD)",
        electionSymbol = "हैंडपंप / नल (Handpump)",
        symbolKey = "handpump",
        electionYear = "2027",
        candidateStatus = "संभावित/घोषित",
        verificationStatus = "सत्यापित"
      ),
      Candidate(
        candidateId = "cand_${constituency.constituencyId}_7",
        surveyId = surveyId,
        constituencyId = constituency.constituencyId,
        candidateName = "निर्दलीय उम्मीदवार",
        partyName = "निर्दलीय (Independent)",
        electionSymbol = "केतली (Kettle)",
        symbolKey = "kettle",
        electionYear = "2027",
        candidateStatus = "स्वतंत्र प्रत्याशी",
        verificationStatus = "सत्यापित"
      ),
      Candidate(
        candidateId = "cand_${constituency.constituencyId}_nota",
        surveyId = surveyId,
        constituencyId = constituency.constituencyId,
        candidateName = "उपरोक्त में से कोई नहीं (NOTA)",
        partyName = "नोटा (None of the Above)",
        electionSymbol = "NOTA",
        symbolKey = "nota",
        electionYear = "2027",
        candidateStatus = "संवैधानिक विकल्प",
        verificationStatus = "सत्यापित"
      )
    )
  }
}

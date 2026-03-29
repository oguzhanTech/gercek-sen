import { mkdirSync, writeFileSync } from "fs";
import { dirname, join } from "path";
import { fileURLToPath } from "url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const ROOT = join(__dirname, "..");
const OUT = join(ROOT, "app", "src", "main", "assets", "quiz_catalog.json");

function o(text, scores) {
  return { text, scores };
}

function q(text, options, image) {
  const r = { text, options };
  if (image) r.image = image;
  return r;
}

function refl_it_a() {
    return {
        "whyPreview": "Doğruyu görmek senin için önemli; bazen bu ‘düzeltme baskısı’ gibi hissedilir.",
        "whyBody": "Detay fark edince durmak zor gelir — çünkü netlik sana güven verir. Karşı taraf ise bazen ‘yargılandım’ veya ‘küçümsendim’ diye okuyabilir; niyetin farklı olsa da ton ve zamanlama mesajı değiştirir. Bu çoğu zaman dikkat ve düzen ihtiyacından gelir; kötülükten değil.",
        "balancePreview": "Önce duy, sonra düzelt; sıra bazen dinlemektir.",
        "balanceTips": [
            "Bir cümlelik yansıtma: ‘Anladığım şu… doğru mu?’ sonra düzeltme.",
            "Küçük hatalarda bilerek pas geçme pratiği (özellikle özel sohbette).",
            "‘Şu an düzeltmek mi, yoksa ilişkiyi korumak mı daha önemli?’ diye kendine sor.",
            "Yazılı ortamda tek paragraf yerine kısa madde + nazik ton.",
        ],
        "todayPreview": "Bir kez sadece dinleyici ol.",
        "todayAction": "Bugün bir sohbette bilerek düzeltmeden 10 dakika kal: sadece ‘hı hı’ ve özetleyici bir cümle dene.",
    };
}

function refl_it_b() {
    return {
        "whyPreview": "Başarı ve adalet duygun güçlü; kıyaslama bazen fark etmeden devreye girer.",
        "whyBody": "İlerlemek ve hak etmek senin için doğal motivasyon. Bazen bu, ‘ben de, ben daha’ gibi hafif gölgelerle kendini gösterir; çoğu zaman niyet yarış değil, kendini ispat etme ihtiyacıdır. Yakın çevre bunu mikro mimik ve cümle tonundan hisseder — sen fark etmeden.",
        "balancePreview": "Kendi skor tahtanı içeride tut; dışarıda paylaşılan alan genişler.",
        "balanceTips": [
            "Başkasının başarısına tek cümlelik içten tebrik (kısa ama net).",
            "‘Ben bugün neyi iyi yaptım?’ diye gün sonu notu — dışa kıyas yerine içe ölçü.",
            "Rekabet hissi gelince 60 saniye duraklat: gerçekten ne tehdit ediyor?",
            "Yakınlıkta ‘kazan/kaybet’ yerine ‘beraber’ kelimelerini bilinçli kullan.",
        ],
        "todayPreview": "Küçük bir iç tebrik yeter.",
        "todayAction": "Bugün birine içten bir ‘helal olsun / çok iyi yapmışsın’ yaz — kıyaslama cümlesi eklemeden.",
    };
}

function refl_it_c() {
    return {
        "whyPreview": "İçeride çok şey dönerken dışarı az sinyal vermek yorucu olabilir.",
        "whyBody": "Bazen düşünmek, sindirmek ve doğru kelimeyi beklemek uzun sürer. Bu tempo dışarıdan ‘sessiz’ veya ‘kapalı’ sanılabilir; oysa içeride sürekli bir işleyiş vardır. Yakın çevre “ne olduğunu bilemeden” merak edebilir — bu da senin için ekstra bir yük gibi hissedilir. Bu bir bozukluk değil; sadece iç/dış ritim farkı.",
        "balancePreview": "Küçük sinyaller büyük yükü azaltır.",
        "balanceTips": [
            "Kısa bir ‘şu an şöyleyim’ cümlesi bile beklentiyi yumuşatır.",
            "Yazılı mesajda tek satırlık durum paylaşımı (yorgun / düşünüyorum) yeterli olabilir.",
            "Sessiz kaldığın anları nazikçe adlandır: ‘biraz içime çekildim’ gibi.",
            "Kendinden önce ‘ne hissettiğini’ söylemek, açıklamadan önce gelir.",
        ],
        "todayPreview": "Tek bir küçük işaret yeter.",
        "todayAction": "Bugün birine tek cümleyle iç halini yaz veya söyle: örn. ‘Bugün biraz içimdeyim, akşam konuşuruz.’",
    };
}

function refl_it_d() {
    return {
        "whyPreview": "Hikâyeni büyük yaşamak doğal; bazen odağı tek kişide toplar.",
        "whyBody": "Duygularını ve anıları canlı anlatırsın; bu yakın çevre için enerji verici olabilir. Aynı anda ‘sahne hep sende’ hissi oluşursa insanlar nefes almak ister. Bu ego değil, çoğu zaman yoğun deneyim ve ifade ihtiyacı — ama denge, paylaşılan mikrofonla gelir.",
        "balancePreview": "Spotlight’ı 30 saniye paylaşmak ilişkiyi hafifletir.",
        "balanceTips": [
            "Anlatımından sonra bilerek sor: ‘Sen ne düşündün?’",
            "Uzun monolog yerine parça parça + ara soru.",
            "‘Bu an benim için böyleydi’ cümlesinden sonra duraklat.",
            "Grup sohbetinde bir tur ‘sadece dinleme’ modu dene.",
        ],
        "todayPreview": "Bir soru, bir sahne paylaşımı.",
        "todayAction": "Bugün bir sohbette konuyu bilerek birine devret: ‘Bu konuda sen ne yapardın?’ diye sor ve 2 dakika sadece dinle.",
    };
}

function main() {
    const catalog = {
        "schemaVersion": 1,
        "categories": [
            {"id": "cat_social", "name": "Sosyal Algı", "shortDescription": "İlk izlenim ve enerjin", "iconGlyph": "Social"},
            {"id": "cat_character", "name": "Karakter", "shortDescription": "Grup içi rolün", "iconGlyph": "Character"},
            {"id": "cat_relations", "name": "İlişkiler", "shortDescription": "Yakınlık ve sınır", "iconGlyph": "Relations"},
            {"id": "cat_truth", "name": "Eğlenceli / Sert Gerçekler", "shortDescription": "Stres altında sen", "iconGlyph": "Truth"},
            {"id": "cat_strength", "name": "Güçlü Yönler", "shortDescription": "Gizli süper gücün", "iconGlyph": "Strength"},
        ],
        "quizzes": [],
    }

    // Quiz 1: first impression
    catalog.quizzes.push(
        {
            "id": "quiz_first_impression",
            "title": "İnsanlar seni ilk tanıştığında nasıl algılıyor?",
            "subtitle": "İlk izlenim enerjini keşfet",
            "categoryId": "cat_social",
            "tags": ["POPULAR", "MOST_SHARED"],
            "coverImage": "sample_quiz_cover",
            "resultTieBreakOrder": ["fi_a", "fi_b", "fi_c", "fi_d"],
            "questions": [
                q(
                    "Yeni bir ortama girdiğinde ilk refleksin ne?",
                    [
                        o("Göz teması ve küçük bir selam", {"fi_a": 2}),
                        o("Köşeden izleyip haritayı çıkarmak", {"fi_b": 2}),
                        o("Ortamı hafifleten bir şaka / yorum", {"fi_c": 2}),
                        o("Yerimi bulup sakin durmak", {"fi_d": 2}),
                    ],
                ),
                q(
                    "Biriyle tanışırken en çok neyi kontrol edersin?",
                    [
                        o("Rahatladı mı?", {"fi_a": 2}),
                        o("Ne kadarını paylaşmalıyım?", {"fi_b": 2}),
                        o("Akış dursun istemem", {"fi_c": 2}),
                        o("Doğru anı beklerim", {"fi_d": 2}),
                    ],
                ),
                q(
                    "Sohbette seni anlatan kelime hangisine yakın?",
                    [
                        o("Dinleyen", {"fi_a": 2, "fi_d": 1}),
                        o("Gözlemci", {"fi_b": 2}),
                        o("Anlatan", {"fi_c": 2}),
                        o("Net", {"fi_d": 2}),
                    ],
                ),
                q(
                    "İlk 5 dakikada insanlar senden ne hisseder (sence)?",
                    [
                        o("Kolay yaklaşılır", {"fi_a": 2}),
                        o("Çözülmesi gereken bir puzzle", {"fi_b": 2}),
                        o("Enerjisi yüksek", {"fi_c": 2}),
                        o("Yerinde ve dengeli", {"fi_d": 2}),
                    ],
                ),
                q(
                    "Grup içinde tanışma anında nerede durursun?",
                    [
                        o("Merkeze yakın, herkesi içeri alan", {"fi_a": 2}),
                        o("Kenarda seçici", {"fi_b": 2}),
                        o("Muhabbeti ateşleyen", {"fi_c": 2}),
                        o("Sakin bir sabit nokta", {"fi_d": 2}),
                    ],
                ),
                q(
                    "Yanlış anlaşıldığını hissedersen ilk tepkin?",
                    [
                        o("Yumuşatır, açıklarım", {"fi_a": 2}),
                        o("Biraz geri çekilirim", {"fi_b": 2}),
                        o("Espriyle dönerim", {"fi_c": 2}),
                        o("Kısa ve net düzeltirim", {"fi_d": 2}),
                    ],
                ),
                q(
                    "İnsanlar seni hatırlayınca ilk ne çıkar (dürüst tahmin)?",
                    [
                        o("İyi hissettirdi", {"fi_a": 2}),
                        o("Tam anlayamadım ama merak ettim", {"fi_b": 2}),
                        o("Eğlenceli anlar", {"fi_c": 2}),
                        o("Güven verdi", {"fi_d": 2}),
                    ],
                ),
            ],
            "results": [
                {
                    "id": "fi_a",
                    "title": "Sıcak çekim alanı",
                    "subtitle": "Yakınlık veriyorsun",
                    "description": "İlk dakikada insanlar sende güvenli bir sıcaklık hisseder; gülümsemen ve tempoın ‘burası rahat’ mesajı verir. Merak edilirsin ama baskıcı değilsin — bu yüzden sohbet doğal açılır.",
                    "shareTeaser": "İlk izlenimde insanlar bana sıcaklık ve rahatlık hissi veriyorum diyorlar. Seninki ne çıktı?",
                    "accent": "Coral",
                },
                {
                    "id": "fi_b",
                    "title": "Seçici sır perdesi",
                    "subtitle": "Mesafen bir filtre",
                    "description": "İlk tanışmada biraz daha temkinlisin; bu bazen ‘mesafeli’ değil, ‘kaliteli seçen’ gibi okunur. Yaklaşanlar merak eder: içeride ne var?",
                    "shareTeaser": "İlk buluşmada mesafem bir merak kapısı gibiymiş. Gerçek Sen’de sonucum bu!",
                    "accent": "Violet",
                },
                {
                    "id": "fi_c",
                    "title": "Odak noktası",
                    "subtitle": "Enerjin fark ediliyor",
                    "description": "Odaya girince ritim değişir: espri, tempo veya duruşunla dikkat çekiyorsun. İnsanlar seni ‘hatırlanır biri’ olarak kodlar.",
                    "shareTeaser": "İlk izlenimde enerjimle hatırlanılıyormuşum. Sen de dene: Gerçek Sen.",
                    "accent": "Amber",
                },
                {
                    "id": "fi_d",
                    "title": "Sessiz otorite",
                    "subtitle": "Az konuş, çok otursun",
                    "description": "Abartısız bir dinginlik taşıyorsun. İnsanlar bunu ‘güvenilir’ ve ‘kaygan olmayan’ olarak yorumlar; sözün az ama ağırlığı çok sanılır.",
                    "shareTeaser": "İlk tanışmada sakinliğim güven veriyormuş. Sonuç beni şaşırttı.",
                    "accent": "Teal",
                },
            ],
        }
    );

    // Quiz 2: group role
    catalog.quizzes.push(
        {
            "id": "quiz_group_role",
            "title": "Arkadaş grubunda hangi rolü üstleniyorsun?",
            "subtitle": "Grup dinamiğindeki yerin",
            "categoryId": "cat_character",
            "tags": ["POPULAR"],
            "coverImage": null,
            "resultTieBreakOrder": ["gr_a", "gr_b", "gr_c", "gr_d"],
            "questions": [
                q(
                    "Plan yapılırken rolün genelde ne?",
                    [
                        o("Herkesin sesini alırım", {"gr_a": 2}),
                        o("Takvimi ve detayı ben toparlarım", {"gr_b": 2}),
                        o("Sıkıcı kısmı espriyle geçiririm", {"gr_c": 2}),
                        o("Asıl önemli olan ne, ona odaklanırım", {"gr_d": 2}),
                    ],
                ),
                q(
                    "Bir tartışma çıktı; ilk hamlen?",
                    [
                        o("Arabuluculuk", {"gr_a": 2}),
                        o("Kuralları ve netliği hatırlatırım", {"gr_b": 2}),
                        o("Gerginliği düşürmek için şaka", {"gr_c": 2}),
                        o("Gerçeği nazikçe söylerim", {"gr_d": 2}),
                    ],
                ),
                q(
                    "Etkinlik günü seni tanımlayan şey?",
                    [
                        o("Kimse dışlanmasın diye uğraşırım", {"gr_a": 2}),
                        o("Zaman ve mekan kontrolü", {"gr_b": 2}),
                        o("Anı fotoğrafları ve kahkahalar", {"gr_c": 2}),
                        o("Kaliteli sohbet köşesi", {"gr_d": 2}),
                    ],
                ),
                q(
                    "Grup sohbetinde senin imzan?",
                    [
                        o("Empati ve destek mesajları", {"gr_a": 2}),
                        o("Pratik bilgi / link / konum", {"gr_b": 2}),
                        o("Sticker, espri, meme", {"gr_c": 2}),
                        o("Uzun ama düşündürücü yazı", {"gr_d": 2}),
                    ],
                ),
                q(
                    "Biri üzüldüğünde yaklaşımın?",
                    [
                        o("Yanında olur, dinlerim", {"gr_a": 2}),
                        o("Çözüm ve adım öneririm", {"gr_b": 2}),
                        o("Moral verir, ortamı yumuşatırım", {"gr_c": 2}),
                        o("Kök nedeni konuşuruz", {"gr_d": 2}),
                    ],
                ),
                q(
                    "Sen olmasan grupta eksik kalır dediğin şey?",
                    [
                        o("Bir arada hissi", {"gr_a": 2}),
                        o("İşleyen düzen", {"gr_b": 2}),
                        o("Kahkaha ve spontane anlar", {"gr_c": 2}),
                        o("Dürüst ayna", {"gr_d": 2}),
                    ],
                ),
                q(
                    "Arkadaşların seni hangi cümleyle anlatır?",
                    [
                        o("‘Onun yanında rahatım’", {"gr_a": 2}),
                        o("‘O olmasa dağılırdık’", {"gr_b": 2}),
                        o("‘Komikliği o getirir’", {"gr_c": 2}),
                        o("‘Derin konuşuruz’", {"gr_d": 2}),
                    ],
                ),
                q(
                    "Grup kararı çıkmıyor; sen?",
                    [
                        o("Ortayı buluruz der, köprü kurarım", {"gr_a": 2}),
                        o("Seçenekleri listeler, karar verdiririm", {"gr_b": 2}),
                        o("‘En azından şunu yapalım’ derim", {"gr_c": 2}),
                        o("Asıl mesele şu diye netleştiririm", {"gr_d": 2}),
                    ],
                ),
            ],
            "results": [
                {
                    "id": "gr_a",
                    "title": "Bağlayıcı yapıştırıcı",
                    "subtitle": "Grubu bir arada tutan sensin",
                    "description": "Çatışmayı yumuşatır, herkesi devreye sokarsın. İnsanlar yanında ‘biz olduk’ hissini bulur; sen olmayınca boşluk hissedilir.",
                    "shareTeaser": "Grupta ben ‘bağlayıcı’ çıkmışım. Rolün neymiş bak: Gerçek Sen.",
                    "accent": "Coral",
                },
                {
                    "id": "gr_b",
                    "title": "Plan ve iskelet",
                    "subtitle": "Organize eden beyin",
                    "description": "Kim, ne zaman, nerede — sende netleşir. Dağınıklığı sevmeyişin bazen ‘kaptan’ gibi okunur; güvenilir bir omurgasın.",
                    "shareTeaser": "Arkadaş grubunda organize taraf benmişim. Quiz sonucu çok isabetli.",
                    "accent": "Teal",
                },
                {
                    "id": "gr_c",
                    "title": "Mizah motoru",
                    "subtitle": "Gerginliği kıran ses",
                    "description": "Anı yaşatırsın; tıkanan yerde bir cümleyle açılır. İnsanlar seninle ‘daha hafif’ hisseder.",
                    "shareTeaser": "Grupta mizah motoru çıktım — gerginliği ben dağıtıyormuşum.",
                    "accent": "Amber",
                },
                {
                    "id": "gr_d",
                    "title": "Derinlik dalgıcı",
                    "subtitle": "Yüzeyin altını soran",
                    "description": "Muhabbeti ‘eğlence’de bırakmazsın; anlam, dürüstlük ve samimiyet istersin. Yakın çevren bunu bilir.",
                    "shareTeaser": "Grup içinde derin sohbet tarafıymışım. Sen de keşfet.",
                    "accent": "Violet",
                },
            ],
        }
    );

    // Quiz 3: relations giving
    catalog.quizzes.push(
        {
            "id": "quiz_relations_giving",
            "title": "İlişkilerde fazla veren taraf mısın yoksa mesafeli mi?",
            "subtitle": "Yakınlık stilin",
            "categoryId": "cat_relations",
            "tags": ["NEW"],
            "coverImage": null,
            "resultTieBreakOrder": ["rel_a", "rel_b", "rel_c", "rel_d"],
            "questions": [
                q(
                    "Mesajlaşmada genelde kim başlatır?",
                    [
                        o("Ben çoğu zaman", {"rel_a": 2}),
                        o("Dengeli gider", {"rel_b": 1, "rel_d": 1}),
                        o("Karşı taraf; ben seçiciyim", {"rel_c": 2}),
                        o("Duruma göre değişir", {"rel_d": 2}),
                    ],
                ),
                q(
                    "Biri üzgün; ilk içgüdün?",
                    [
                        o("Hemen destek modu", {"rel_a": 2}),
                        o("Yardım ederim ama tükenmemeye dikkat", {"rel_b": 2}),
                        o("Önce kendi alanımı korurum", {"rel_c": 2}),
                        o("Ne kadar müdahale etmeliyim tartarım", {"rel_d": 2}),
                    ],
                ),
                q(
                    "Hayır demek senin için?",
                    [
                        o("Zor, çoğu zaman evet derim", {"rel_a": 2}),
                        o("Öğrendim, net söylerim", {"rel_b": 2}),
                        o("Kolay; seçici yaklaşırım", {"rel_c": 2}),
                        o("Çalışıyorum, bazen zorlanırım", {"rel_d": 2}),
                    ],
                ),
                q(
                    "İlişkide en çok korktuğun şey?",
                    [
                        o("Kullanılmak / görülmemek", {"rel_a": 2}),
                        o("Sınırımın çiğnenmesi", {"rel_b": 2}),
                        o("Aşırı yakınlık baskısı", {"rel_c": 2}),
                        o("Dengenin bozulması", {"rel_d": 2}),
                    ],
                ),
                q(
                    "Sevgi dilin hangisine yakın?",
                    [
                        o("Zaman ve ilgi harcamak", {"rel_a": 2}),
                        o("Saygı ve öngörülebilirlik", {"rel_b": 2}),
                        o("Derin ama kontrollü bağ", {"rel_c": 2}),
                        o("Hep biraz karışık ama samimi", {"rel_d": 2}),
                    ],
                ),
                q(
                    "Bir ilişki dinamiğinde rolün?",
                    [
                        o("Besleyen", {"rel_a": 2}),
                        o("Dengeleyen", {"rel_b": 2}),
                        o("Gözlemleyen", {"rel_c": 2}),
                        o("Ayar çeken", {"rel_d": 2}),
                    ],
                ),
                q(
                    "Son olarak: ‘fazla veriyorum’ dediğin oluyor mu?",
                    [
                        o("Evet, sık", {"rel_a": 2}),
                        o("Eskiden evet, şimdi daha az", {"rel_b": 2}),
                        o("Hayır, mesafem korur", {"rel_c": 2}),
                        o("Bazen evet bazen hayır", {"rel_d": 2}),
                    ],
                ),
            ],
            "results": [
                {
                    "id": "rel_a",
                    "title": "Cömert kalp",
                    "subtitle": "Veren tarafta eğilim",
                    "description": "Sevdiklerin için enerji harcamaktan çekinmezsin. Dikkat: bazen dengeyi unutmadan ‘geri beslenme’yi de talep etmek seni daha da güçlendirir.",
                    "shareTeaser": "İlişkilerde veren taraftaymışım — sonuç beni düşündürdü.",
                    "accent": "Rose",
                },
                {
                    "id": "rel_b",
                    "title": "Sağlıklı sınır ustası",
                    "subtitle": "Yakın ama kontrollü",
                    "description": "Yakınlık istersin ama özünü feda etmezsin. Netliğin ilişkileri uzatır; insanlar sınırını öğrenince daha çok güvenir.",
                    "shareTeaser": "Yakınlık + sınır dengesini iyi kuruyormuşum. Quiz: Gerçek Sen.",
                    "accent": "Teal",
                },
                {
                    "id": "rel_c",
                    "title": "Mesafeli ama sadık",
                    "subtitle": "Derin ama seçici",
                    "description": "Herkesi içeri almazsın; seçtiğin az kişiye çok verirsin. Bu bazen ‘soğuk’ sanılır oysa sadakatin başka türlü.",
                    "shareTeaser": "Mesafem var ama sadakati yoğunmuşum. İlginç sonuç!",
                    "accent": "Violet",
                },
                {
                    "id": "rel_d",
                    "title": "Denge arayan",
                    "subtitle": "Ver-al ritmini ayarlıyorsun",
                    "description": "Bazen fazla verirsin, bazen çekilirsin; aslında doğru ritmi arıyorsun. Farkındalık güçlü tarafın.",
                    "shareTeaser": "İlişkilerde dengeyi ayarlayan taraftaymışım. Sen de dene.",
                    "accent": "Amber",
                },
            ],
        }
    );

    // Quiz 4: stress
    catalog.quizzes.push(
        {
            "id": "quiz_stress",
            "title": "Stresli durumlarda nasıl tepki veriyorsun?",
            "subtitle": "Krizdeki stilin",
            "categoryId": "cat_truth",
            "tags": ["MOST_SHARED"],
            "coverImage": null,
            "resultTieBreakOrder": ["st_a", "st_b", "st_c", "st_d"],
            "questions": [
                q(
                    "Deadline yaklaştı; iç dünyan?",
                    [
                        o("Hızlanırım, elimi çabuk tutarım", {"st_a": 2}),
                        o("Önceliklendiririm, sakin kalırım", {"st_b": 2}),
                        o("Biraz içime çekilirim", {"st_c": 2}),
                        o("Birine danışmak isterim", {"st_d": 2}),
                    ],
                ),
                q(
                    "Tartışma tırmandı; ilk tepki?",
                    [
                        o("Konuyu çözmeye odaklanırım", {"st_a": 2}),
                        o("Nefes alıp mantık kurarım", {"st_b": 2}),
                        o("Sessizleşir, düşünürüm", {"st_c": 2}),
                        o("Arabulucu veya yakın biri isterim", {"st_d": 2}),
                    ],
                ),
                q(
                    "Plansız kötü haber; ne yaparsın?",
                    [
                        o("Hemen alternatif üretirim", {"st_a": 2}),
                        o("Adımları sıraya koyarım", {"st_b": 2}),
                        o("Kısa süre kendime kapanırım", {"st_c": 2}),
                        o("Güvendiğim biriyle paylaşırım", {"st_d": 2}),
                    ],
                ),
                q(
                    "Uyku ve iştah stres altında?",
                    [
                        o("Koşturmaca artar, unuturum", {"st_a": 2}),
                        o("Rutine sıkı sarılırım", {"st_b": 2}),
                        o("Dalgalanır, iç ses yüksek", {"st_c": 2}),
                        o("Yakınlık ararım, rahatlarım", {"st_d": 2}),
                    ],
                ),
                q(
                    "Stres sonrası toparlanma?",
                    [
                        o("Bir şeyleri bitirince rahatlarım", {"st_a": 2}),
                        o("Kontrol listesi tamamlanınca", {"st_b": 2}),
                        o("Zaman ve yalnızlık", {"st_c": 2}),
                        o("Sohbet ve sarılma", {"st_d": 2}),
                    ],
                ),
                q(
                    "Kriz anında çevrendekiler seni nasıl tanımlar?",
                    [
                        o("İşi çözen", {"st_a": 2}),
                        o("Sakin kalan", {"st_b": 2}),
                        o("İçine kapanan ama dönen", {"st_c": 2}),
                        o("Yanında insan isteyen", {"st_d": 2}),
                    ],
                ),
                q(
                    "Son soru: stres senin için çoğunlukla?",
                    [
                        o("Yakıt", {"st_a": 2}),
                        o("Bulmaca", {"st_b": 2}),
                        o("Fırtına", {"st_c": 2}),
                        o("Paylaşılmayı bekleyen yük", {"st_d": 2}),
                    ],
                ),
            ],
            "results": [
                {
                    "id": "st_a",
                    "title": "Aksiyon modu",
                    "subtitle": "Stres = hareket",
                    "description": "Baskı artınca iş yaparsın, çözersin, yön değiştirirsin. Risk: bazen nefes almadan koşarsın; duraksamak da stratejidir.",
                    "shareTeaser": "Stres altında aksiyon moduna giriyormuşum. Gerçek Sen sonucu şaşırttı.",
                    "accent": "Amber",
                },
                {
                    "id": "st_b",
                    "title": "Soğuk kanlı analiz",
                    "subtitle": "Önce sistem, sonra duygu",
                    "description": "Panik yerine listeler, planlar, parçalarsın. İnsanlar yanında ‘krizde ayakta kalan’ tarafı görür.",
                    "shareTeaser": "Stres anında analiz modundaymışım. Quiz’i çöz, seninki ne?",
                    "accent": "Teal",
                },
                {
                    "id": "st_c",
                    "title": "İçe dönüş",
                    "subtitle": "Proses ederek toparlanma",
                    "description": "Önce iç sesini dinlersin; konuşmak veya yazmak sana iyi gelir. Dışarıdan ‘çekildi’ sanılabilir oysa işleyiş böyle.",
                    "shareTeaser": "Stresi içeride işliyormuşum. Bu quiz bunu net söyledi.",
                    "accent": "Violet",
                },
                {
                    "id": "st_d",
                    "title": "Destek arayan",
                    "subtitle": "Yalnız değilim, birlikte atlatırım",
                    "description": "Zor günde yakınlık istersin; paylaşmak yükü hafifletir. Bu güçlü bir strateji — ‘tek başına dayanmak’ şart değil.",
                    "shareTeaser": "Stres altında destek arayan taraftaymışım. Normal ve güçlüymüş aslında.",
                    "accent": "Coral",
                },
            ],
        }
    )

    // Quiz 5: annoying trait (with reflections)
    catalog.quizzes.push(
        {
            "id": "quiz_annoying_trait",
            "title": "Senin en itici özelliğin ne?",
            "subtitle": "Sert ama komik gerçekler",
            "categoryId": "cat_truth",
            "tags": ["NEW", "POPULAR"],
            "coverImage": null,
            "resultTieBreakOrder": ["it_a", "it_b", "it_c", "it_d"],
            "questions": [
                q(
                    "Sohbette en çok hangisi seni tetikler?",
                    [
                        o("Yanlış bilgi geçtiğinde içim cız ediyor", {"it_a": 2}),
                        o("Başkasının övülmesi garip hissettiriyor", {"it_b": 2}),
                        o("Kalabalıkta konuşmak zor geliyor", {"it_c": 2}),
                        o("Konu bana gelmeyince sıkılıyorum", {"it_d": 2}),
                    ],
                    "sample_option_image",
                ),
                q(
                    "Biri hikâye anlatırken sen?",
                    [
                        o("Doğru kelimeyi bulup düzeltirim", {"it_a": 2}),
                        o("Aklıma kendi versiyonum gelir", {"it_b": 2}),
                        o("Dinlerim, az tepki veririm", {"it_c": 2}),
                        o("Ben de benzer bir anımı eklerim", {"it_d": 2}),
                    ],
                ),
                q(
                    "Grup sohbetinde seni en çok hangisi yansıtır?",
                    [
                        o("‘Aslında şöyle’ diye atan mesajlar", {"it_a": 2}),
                        o("Gösterişsiz ama içten içe kıyaslama", {"it_b": 2}),
                        o("Uzun süre sessiz kalıp tek cümle", {"it_c": 2}),
                        o("Konuyu kendi üzerime çeken espriler", {"it_d": 2}),
                    ],
                ),
                q(
                    "Eleştiri aldığında ilk iç ses?",
                    [
                        o("Haklılar mı diye teknik analiz", {"it_a": 2}),
                        o("Ben de onların eksiğini bilirim", {"it_b": 2}),
                        o("İçime kapanır, uzun sürer", {"it_c": 2}),
                        o("Neden bana hep bana deniyor", {"it_d": 2}),
                    ],
                ),
                q(
                    "Yanlış anlaşıldığında?",
                    [
                        o("Net kanıt ve düzeltme paketi", {"it_a": 2}),
                        o("Soğurum, mesafe koyarım", {"it_b": 2, "it_c": 1}),
                        o("Susarım, sonra içerde döner", {"it_c": 2}),
                        o("Açıklarım; biraz dramatik olabilir", {"it_d": 2}),
                    ],
                ),
                q(
                    "İnsanlar senden en çok neyi ‘yorucu’ bulabilir (dürüstçe)?",
                    [
                        o("Her şeyin doğrusunu bilmek istemem", {"it_a": 2}),
                        o("Göze çarpmayan rekabet", {"it_b": 2}),
                        o("Ne düşündüğümü tahmin ettirmemek", {"it_c": 2}),
                        o("Konunun büyümesi ve duygusal yoğunluk", {"it_d": 2}),
                    ],
                ),
                q(
                    "Özür dileme tarzın?",
                    [
                        o("Ama şunu da söylemeliyim diye uzar", {"it_a": 2}),
                        o("Özür + savunma karışımı", {"it_b": 2, "it_a": 1}),
                        o("Kısa ve geç; içeride devam eder", {"it_c": 2}),
                        o("Büyük sahne: özür ve açıklama şovu", {"it_d": 2}),
                    ],
                ),
                q(
                    "Son: ‘itici’ dediğinde hangisi daha yakın (mizahi)?",
                    [
                        o("Düzeltme refleksi", {"it_a": 2}),
                        o("Gizli skor tutma", {"it_b": 2}),
                        o("Sinyal vermeme", {"it_c": 2}),
                        o("Spotlight çekme", {"it_d": 2}),
                    ],
                ),
            ],
            "results": [
                {
                    "id": "it_a",
                    "title": "Yardımsız düzeltici",
                    "subtitle": "Doğru bildiğini herkes duysun istiyor",
                    "description": "Küçük hataları görünce duramıyorsun; senin için netlik, karşı taraf için bazen ‘sürekli düzeltilme’ gibi geliyor. Niyetin iyi — ama bazen insanlar sadece duyulmak ister, düzeltilmek değil.",
                    "shareTeaser": "Gerçek Sen: en itici özelliğim ‘yardımsız düzeltici’ çıktı. Tahmin et bakalım seninki ne?",
                    "accent": "Teal",
                    "reflection": refl_it_a(),
                },
                {
                    "id": "it_b",
                    "title": "Gizli rekabetçi",
                    "subtitle": "Fark ettirmeden yarış modu",
                    "description": "Başarıyı seversin; bazen bu ‘ben de yaptım / ben daha kötü’ gibi hafif gölgelerle kendini gösterir. Kötü niyet değil, alışkanlık — ama yakın çevre bunu hissedebiliyor.",
                    "shareTeaser": "Quiz dedi ki gizli rekabetçiymişim. İtirazım var ama içimden bir ses…",
                    "accent": "Amber",
                    "reflection": refl_it_b(),
                },
                {
                    "id": "it_c",
                    "title": "Okunması zor kitap",
                    "subtitle": "Çok içeridesin, dışarı az sinyal",
                    "description": "Düşünüyorsun, sindiriyorsun, az konuşuyorsun. Senin için dinginlik; bazıları için ‘ne hissediyor acaba?’ yorgunluğu. Açık bir işaret bazen her şeyi kolaylaştırır.",
                    "shareTeaser": "‘Okunması zor kitap’ sonucu aldım. Sert ama biraz da haklı sayılır.",
                    "accent": "Violet",
                    "reflection": refl_it_c(),
                },
                {
                    "id": "it_d",
                    "title": "Ana karakter modu",
                    "subtitle": "Hikâye senin etrafında dönüyor gibi",
                    "description": "Hikâyeleri büyük anlatırsın, dramayı hissedersin — bazen odağı çektiğini fark etmeden. Eğlenceli olabilir; bazen de çevre ‘biraz nefes alalım’ der.",
                    "shareTeaser": "En itici özelliğim ‘ana karakter modu’ymuş. Paylaşmadan duramadım.",
                    "accent": "Coral",
                    "reflection": refl_it_d(),
                },
            ],
        }
    );

    // Quiz 6: hidden strength
    catalog.quizzes.push(
        {
            "id": "quiz_hidden_strength",
            "title": "Gizli güçlü yönün aslında ne?",
            "subtitle": "Fark etmediğin süper güç",
            "categoryId": "cat_strength",
            "tags": ["POPULAR", "NEW"],
            "coverImage": null,
            "resultTieBreakOrder": ["hs_a", "hs_b", "hs_c", "hs_d"],
            "questions": [
                q(
                    "Zor bir günde seni ayakta tutan ne?",
                    [
                        o("İçten bir ‘devam’ sesi", {"hs_a": 2}),
                        o("İçimdeki ‘bir şeyler ters’ uyarısı", {"hs_b": 2}),
                        o("Anlam arayışı", {"hs_c": 2}),
                        o("Bir çıkış yolu bulma isteği", {"hs_d": 2}),
                    ],
                ),
                q(
                    "Bir problem çıktı; güçlü olduğun an?",
                    [
                        o("Kimse görmeden sabrettiğim anlar", {"hs_a": 2}),
                        o("İlk seferde doğru kokladığım an", {"hs_b": 2}),
                        o("Birine doğru soruyu sorduğum an", {"hs_c": 2}),
                        o("Elindekiyle yetinip çözdüğüm an", {"hs_d": 2}),
                    ],
                ),
                q(
                    "İnsanlar senden gizlice ne öğreniyor?",
                    [
                        o("Düşmeden yürümeyi", {"hs_a": 2}),
                        o("İyi okumayı", {"hs_b": 2}),
                        o("Kendini dinlemeyi", {"hs_c": 2}),
                        o("Yaratıcı pratikliği", {"hs_d": 2}),
                    ],
                ),
                q(
                    "Takdir edilmediğini düşündüğün yetenek?",
                    [
                        o("Dayanıklılık", {"hs_a": 2}),
                        o("Sezgi", {"hs_b": 2}),
                        o("Derin dinleme / sorgulama", {"hs_c": 2}),
                        o("Improvizasyon", {"hs_d": 2}),
                    ],
                ),
                q(
                    "Boş zamanında zihnin?",
                    [
                        o("Geçmişi sindirir, toparlarım", {"hs_a": 2}),
                        o("Desenleri fark ederim", {"hs_b": 2}),
                        o("Anlamlı şeyler okur/konuşurum", {"hs_c": 2}),
                        o("Projeler ve fikirler üretirim", {"hs_d": 2}),
                    ],
                ),
                q(
                    "Birine güç vermek istesen nasıl yaparsın?",
                    [
                        o("Yanında olurum", {"hs_a": 2, "hs_c": 1}),
                        o("Doğru yolu gösteririm", {"hs_b": 2}),
                        o("Kendini görmesini sağlarım", {"hs_c": 2}),
                        o("Somut bir çözüm sunarım", {"hs_d": 2}),
                    ],
                ),
                q(
                    "Son: gizli güç dediğinde hangisi yakın?",
                    [
                        o("Sabır", {"hs_a": 2}),
                        o("Sezgi", {"hs_b": 2}),
                        o("Merak", {"hs_c": 2}),
                        o("Buluşçuluk", {"hs_d": 2}),
                    ],
                ),
                q(
                    "Kendini hangi metaforla seversin?",
                    [
                        o("Sessiz kök", {"hs_a": 2}),
                        o("İnce radar", {"hs_b": 2}),
                        o("Açan soru", {"hs_c": 2}),
                        o("Eldeki malzeme şef’i", {"hs_d": 2}),
                    ],
                ),
            ],
            "results": [
                {
                    "id": "hs_a",
                    "title": "Sessiz dayanıklılık",
                    "subtitle": "Çok şeyi kırmadan taşırsın",
                    "description": "Gürültüsüz ama inatçı bir iç gücün var. İnsanlar bazen fark etmez; kritik anlarda ‘hâlâ ayaktasın’ gerçeği ortaya çıkar.",
                    "shareTeaser": "Gizli gücüm sessiz dayanıklılıkmış. Gerçek Sen bunu söyledi.",
                    "accent": "Teal",
                },
                {
                    "id": "hs_b",
                    "title": "Sezgisel keskinlik",
                    "subtitle": "Okumadan anlarsın",
                    "description": "Detayı kaçırmadan ‘hisseder’sin. Kararlarında mantık kadar içgüdün de rol alır ve çoğu zaman isabet eder.",
                    "shareTeaser": "Güçlü yönüm sezgiymiş. Quiz sonucu paylaşılası.",
                    "accent": "Violet",
                },
                {
                    "id": "hs_c",
                    "title": "İnsanları açan merak",
                    "subtitle": "Soru sorarak güç verirsin",
                    "description": "Doğru soruyla insanlar kendini daha net görür. Bu nadiren övülür ama ilişkilerde büyüyen etki yaratır.",
                    "shareTeaser": "Gizli süper gücüm: merakla açmakmış. Dene: Gerçek Sen.",
                    "accent": "Coral",
                },
                {
                    "id": "hs_d",
                    "title": "Pratik yaratıcılık",
                    "subtitle": "Kısıtta çözüm üretirsin",
                    "description": "Kaynak azken en iyi fikirler senden çıkar. ‘Olur mu?’ dedirten ama işe yarayan yollar bulursun.",
                    "shareTeaser": "Kısıtta parlayan pratik yaratıcılıkmışım. Sonuç güzel.",
                    "accent": "Amber",
                },
            ],
        }
    );

    mkdirSync(dirname(OUT), { recursive: true });
    writeFileSync(OUT, JSON.stringify(catalog, null, 2), "utf8");
    console.log("Wrote", OUT);
}

main();

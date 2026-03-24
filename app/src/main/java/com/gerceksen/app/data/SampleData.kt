package com.gerceksen.app.data

import com.gerceksen.app.R
import com.gerceksen.app.model.AnswerOption
import com.gerceksen.app.model.Category
import com.gerceksen.app.model.CategoryIcon
import com.gerceksen.app.model.Question
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.model.QuizResultDefinition
import com.gerceksen.app.model.QuizTag
import com.gerceksen.app.model.ResultAccent

private fun o(
    text: String,
    vararg pairs: Pair<String, Int>,
    imageResId: Int? = null,
) = AnswerOption(text, mapOf(*pairs), imageResId)

object SampleData {

    val categories: List<Category> = listOf(
        Category(
            id = "cat_social",
            name = "Sosyal Algı",
            shortDescription = "İlk izlenim ve enerjin",
            iconGlyph = CategoryIcon.Social,
        ),
        Category(
            id = "cat_character",
            name = "Karakter",
            shortDescription = "Grup içi rolün",
            iconGlyph = CategoryIcon.Character,
        ),
        Category(
            id = "cat_relations",
            name = "İlişkiler",
            shortDescription = "Yakınlık ve sınır",
            iconGlyph = CategoryIcon.Relations,
        ),
        Category(
            id = "cat_truth",
            name = "Eğlenceli / Sert Gerçekler",
            shortDescription = "Stres altında sen",
            iconGlyph = CategoryIcon.Truth,
        ),
        Category(
            id = "cat_strength",
            name = "Güçlü Yönler",
            shortDescription = "Gizli süper gücün",
            iconGlyph = CategoryIcon.Strength,
        ),
    )

    val quizzes: List<Quiz> = listOf(
        quizFirstImpression(),
        quizGroupRole(),
        quizGivingInRelations(),
        quizStressReaction(),
        quizMostAnnoyingTrait(),
        quizHiddenStrength(),
    )

    private fun quizFirstImpression(): Quiz {
        val results = listOf(
            QuizResultDefinition(
                id = "fi_a",
                title = "Sıcak çekim alanı",
                subtitle = "Yakınlık veriyorsun",
                description = "İlk dakikada insanlar sende güvenli bir sıcaklık hisseder; gülümsemen ve tempoın ‘burası rahat’ mesajı verir. Merak edilirsin ama baskıcı değilsin — bu yüzden sohbet doğal açılır.",
                shareTeaser = "İlk izlenimde insanlar bana sıcaklık ve rahatlık hissi veriyorum diyorlar. Seninki ne çıktı?",
                accent = ResultAccent.Coral,
            ),
            QuizResultDefinition(
                id = "fi_b",
                title = "Seçici sır perdesi",
                subtitle = "Mesafen bir filtre",
                description = "İlk tanışmada biraz daha temkinlisin; bu bazen ‘mesafeli’ değil, ‘kaliteli seçen’ gibi okunur. Yaklaşanlar merak eder: içeride ne var?",
                shareTeaser = "İlk buluşmada mesafem bir merak kapısı gibiymiş. Gerçek Sen’de sonucum bu!",
                accent = ResultAccent.Violet,
            ),
            QuizResultDefinition(
                id = "fi_c",
                title = "Odak noktası",
                subtitle = "Enerjin fark ediliyor",
                description = "Odaya girince ritim değişir: espri, tempo veya duruşunla dikkat çekiyorsun. İnsanlar seni ‘hatırlanır biri’ olarak kodlar.",
                shareTeaser = "İlk izlenimde enerjimle hatırlanılıyormuşum. Sen de dene: Gerçek Sen.",
                accent = ResultAccent.Amber,
            ),
            QuizResultDefinition(
                id = "fi_d",
                title = "Sessiz otorite",
                subtitle = "Az konuş, çok otursun",
                description = "Abartısız bir dinginlik taşıyorsun. İnsanlar bunu ‘güvenilir’ ve ‘kaygan olmayan’ olarak yorumlar; sözün az ama ağırlığı çok sanılır.",
                shareTeaser = "İlk tanışmada sakinliğim güven veriyormuş. Sonuç beni şaşırttı.",
                accent = ResultAccent.Teal,
            ),
        )
        val order = listOf("fi_a", "fi_b", "fi_c", "fi_d")
        val qs = listOf(
            Question(
                "Yeni bir ortama girdiğinde ilk refleksin ne?",
                listOf(
                    o("Göz teması ve küçük bir selam", "fi_a" to 2),
                    o("Köşeden izleyip haritayı çıkarmak", "fi_b" to 2),
                    o("Ortamı hafifleten bir şaka / yorum", "fi_c" to 2),
                    o("Yerimi bulup sakin durmak", "fi_d" to 2),
                ),
            ),
            Question(
                "Biriyle tanışırken en çok neyi kontrol edersin?",
                listOf(
                    o("Rahatladı mı?", "fi_a" to 2),
                    o("Ne kadarını paylaşmalıyım?", "fi_b" to 2),
                    o("Akış dursun istemem", "fi_c" to 2),
                    o("Doğru anı beklerim", "fi_d" to 2),
                ),
            ),
            Question(
                "Sohbette seni anlatan kelime hangisine yakın?",
                listOf(
                    o("Dinleyen", "fi_a" to 2, "fi_d" to 1),
                    o("Gözlemci", "fi_b" to 2),
                    o("Anlatan", "fi_c" to 2),
                    o("Net", "fi_d" to 2),
                ),
            ),
            Question(
                "İlk 5 dakikada insanlar senden ne hisseder (sence)?",
                listOf(
                    o("Kolay yaklaşılır", "fi_a" to 2),
                    o("Çözülmesi gereken bir puzzle", "fi_b" to 2),
                    o("Enerjisi yüksek", "fi_c" to 2),
                    o("Yerinde ve dengeli", "fi_d" to 2),
                ),
            ),
            Question(
                "Grup içinde tanışma anında nerede durursun?",
                listOf(
                    o("Merkeze yakın, herkesi içeri alan", "fi_a" to 2),
                    o("Kenarda seçici", "fi_b" to 2),
                    o("Muhabbeti ateşleyen", "fi_c" to 2),
                    o("Sakin bir sabit nokta", "fi_d" to 2),
                ),
            ),
            Question(
                "Yanlış anlaşıldığını hissedersen ilk tepkin?",
                listOf(
                    o("Yumuşatır, açıklarım", "fi_a" to 2),
                    o("Biraz geri çekilirim", "fi_b" to 2),
                    o("Espriyle dönerim", "fi_c" to 2),
                    o("Kısa ve net düzeltirim", "fi_d" to 2),
                ),
            ),
            Question(
                "İnsanlar seni hatırlayınca ilk ne çıkar (dürüst tahmin)?",
                listOf(
                    o("İyi hissettirdi", "fi_a" to 2),
                    o("Tam anlayamadım ama merak ettim", "fi_b" to 2),
                    o("Eğlenceli anlar", "fi_c" to 2),
                    o("Güven verdi", "fi_d" to 2),
                ),
            ),
        )
        return Quiz(
            id = "quiz_first_impression",
            title = "İnsanlar seni ilk tanıştığında nasıl algılıyor?",
            subtitle = "İlk izlenim enerjini keşfet",
            categoryId = "cat_social",
            tags = setOf(QuizTag.POPULAR, QuizTag.MOST_SHARED),
            questions = qs,
            resultDefinitions = results,
            resultTieBreakOrder = order,
        )
    }

    private fun quizGroupRole(): Quiz {
        val results = listOf(
            QuizResultDefinition(
                id = "gr_a",
                title = "Bağlayıcı yapıştırıcı",
                subtitle = "Grubu bir arada tutan sensin",
                description = "Çatışmayı yumuşatır, herkesi devreye sokarsın. İnsanlar yanında ‘biz olduk’ hissini bulur; sen olmayınca boşluk hissedilir.",
                shareTeaser = "Grupta ben ‘bağlayıcı’ çıkmışım. Rolün neymiş bak: Gerçek Sen.",
                accent = ResultAccent.Coral,
            ),
            QuizResultDefinition(
                id = "gr_b",
                title = "Plan ve iskelet",
                subtitle = "Organize eden beyin",
                description = "Kim, ne zaman, nerede — sende netleşir. Dağınıklığı sevmeyişin bazen ‘kaptan’ gibi okunur; güvenilir bir omurgasın.",
                shareTeaser = "Arkadaş grubunda organize taraf benmişim. Quiz sonucu çok isabetli.",
                accent = ResultAccent.Teal,
            ),
            QuizResultDefinition(
                id = "gr_c",
                title = "Mizah motoru",
                subtitle = "Gerginliği kıran ses",
                description = "Anı yaşatırsın; tıkanan yerde bir cümleyle açılır. İnsanlar seninle ‘daha hafif’ hisseder.",
                shareTeaser = "Grupta mizah motoru çıktım — gerginliği ben dağıtıyormuşum.",
                accent = ResultAccent.Amber,
            ),
            QuizResultDefinition(
                id = "gr_d",
                title = "Derinlik dalgıcı",
                subtitle = "Yüzeyin altını soran",
                description = "Muhabbeti ‘eğlence’de bırakmazsın; anlam, dürüstlük ve samimiyet istersin. Yakın çevren bunu bilir.",
                shareTeaser = "Grup içinde derin sohbet tarafıymışım. Sen de keşfet.",
                accent = ResultAccent.Violet,
            ),
        )
        val order = listOf("gr_a", "gr_b", "gr_c", "gr_d")
        val qs = listOf(
            Question(
                "Plan yapılırken rolün genelde ne?",
                listOf(
                    o("Herkesin sesini alırım", "gr_a" to 2),
                    o("Takvimi ve detayı ben toparlarım", "gr_b" to 2),
                    o("Sıkıcı kısmı espriyle geçiririm", "gr_c" to 2),
                    o("Asıl önemli olan ne, ona odaklanırım", "gr_d" to 2),
                ),
            ),
            Question(
                "Bir tartışma çıktı; ilk hamlen?",
                listOf(
                    o("Arabuluculuk", "gr_a" to 2),
                    o("Kuralları ve netliği hatırlatırım", "gr_b" to 2),
                    o("Gerginliği düşürmek için şaka", "gr_c" to 2),
                    o("Gerçeği nazikçe söylerim", "gr_d" to 2),
                ),
            ),
            Question(
                "Etkinlik günü seni tanımlayan şey?",
                listOf(
                    o("Kimse dışlanmasın diye uğraşırım", "gr_a" to 2),
                    o("Zaman ve mekan kontrolü", "gr_b" to 2),
                    o("Anı fotoğrafları ve kahkahalar", "gr_c" to 2),
                    o("Kaliteli sohbet köşesi", "gr_d" to 2),
                ),
            ),
            Question(
                "Grup sohbetinde senin imzan?",
                listOf(
                    o("Empati ve destek mesajları", "gr_a" to 2),
                    o("Pratik bilgi / link / konum", "gr_b" to 2),
                    o("Sticker, espri, meme", "gr_c" to 2),
                    o("Uzun ama düşündürücü yazı", "gr_d" to 2),
                ),
            ),
            Question(
                "Biri üzüldüğünde yaklaşımın?",
                listOf(
                    o("Yanında olur, dinlerim", "gr_a" to 2),
                    o("Çözüm ve adım öneririm", "gr_b" to 2),
                    o("Moral verir, ortamı yumuşatırım", "gr_c" to 2),
                    o("Kök nedeni konuşuruz", "gr_d" to 2),
                ),
            ),
            Question(
                "Sen olmasan grupta eksik kalır dediğin şey?",
                listOf(
                    o("Bir arada hissi", "gr_a" to 2),
                    o("İşleyen düzen", "gr_b" to 2),
                    o("Kahkaha ve spontane anlar", "gr_c" to 2),
                    o("Dürüst ayna", "gr_d" to 2),
                ),
            ),
            Question(
                "Arkadaşların seni hangi cümleyle anlatır?",
                listOf(
                    o("‘Onun yanında rahatım’", "gr_a" to 2),
                    o("‘O olmasa dağılırdık’", "gr_b" to 2),
                    o("‘Komikliği o getirir’", "gr_c" to 2),
                    o("‘Derin konuşuruz’", "gr_d" to 2),
                ),
            ),
            Question(
                "Grup kararı çıkmıyor; sen?",
                listOf(
                    o("Ortayı buluruz der, köprü kurarım", "gr_a" to 2),
                    o("Seçenekleri listeler, karar verdiririm", "gr_b" to 2),
                    o("‘En azından şunu yapalım’ derim", "gr_c" to 2),
                    o("Asıl mesele şu diye netleştiririm", "gr_d" to 2),
                ),
            ),
        )
        return Quiz(
            id = "quiz_group_role",
            title = "Arkadaş grubunda hangi rolü üstleniyorsun?",
            subtitle = "Grup dinamiğindeki yerin",
            categoryId = "cat_character",
            tags = setOf(QuizTag.POPULAR),
            questions = qs,
            resultDefinitions = results,
            resultTieBreakOrder = order,
        )
    }

    private fun quizGivingInRelations(): Quiz {
        val results = listOf(
            QuizResultDefinition(
                id = "rel_a",
                title = "Cömert kalp",
                subtitle = "Veren tarafta eğilim",
                description = "Sevdiklerin için enerji harcamaktan çekinmezsin. Dikkat: bazen dengeyi unutmadan ‘geri beslenme’yi de talep etmek seni daha da güçlendirir.",
                shareTeaser = "İlişkilerde veren taraftaymışım — sonuç beni düşündürdü.",
                accent = ResultAccent.Rose,
            ),
            QuizResultDefinition(
                id = "rel_b",
                title = "Sağlıklı sınır ustası",
                subtitle = "Yakın ama kontrollü",
                description = "Yakınlık istersin ama özünü feda etmezsin. Netliğin ilişkileri uzatır; insanlar sınırını öğrenince daha çok güvenir.",
                shareTeaser = "Yakınlık + sınır dengesini iyi kuruyormuşum. Quiz: Gerçek Sen.",
                accent = ResultAccent.Teal,
            ),
            QuizResultDefinition(
                id = "rel_c",
                title = "Mesafeli ama sadık",
                subtitle = "Derin ama seçici",
                description = "Herkesi içeri almazsın; seçtiğin az kişiye çok verirsin. Bu bazen ‘soğuk’ sanılır oysa sadakatin başka türlü.",
                shareTeaser = "Mesafem var ama sadakati yoğunmuşum. İlginç sonuç!",
                accent = ResultAccent.Violet,
            ),
            QuizResultDefinition(
                id = "rel_d",
                title = "Denge arayan",
                subtitle = "Ver-al ritmini ayarlıyorsun",
                description = "Bazen fazla verirsin, bazen çekilirsin; aslında doğru ritmi arıyorsun. Farkındalık güçlü tarafın.",
                shareTeaser = "İlişkilerde dengeyi ayarlayan taraftaymışım. Sen de dene.",
                accent = ResultAccent.Amber,
            ),
        )
        val order = listOf("rel_a", "rel_b", "rel_c", "rel_d")
        val qs = listOf(
            Question(
                "Mesajlaşmada genelde kim başlatır?",
                listOf(
                    o("Ben çoğu zaman", "rel_a" to 2),
                    o("Dengeli gider", "rel_b" to 1, "rel_d" to 1),
                    o("Karşı taraf; ben seçiciyim", "rel_c" to 2),
                    o("Duruma göre değişir", "rel_d" to 2),
                ),
            ),
            Question(
                "Biri üzgün; ilk içgüdün?",
                listOf(
                    o("Hemen destek modu", "rel_a" to 2),
                    o("Yardım ederim ama tükenmemeye dikkat", "rel_b" to 2),
                    o("Önce kendi alanımı korurum", "rel_c" to 2),
                    o("Ne kadar müdahale etmeliyim tartarım", "rel_d" to 2),
                ),
            ),
            Question(
                "Hayır demek senin için?",
                listOf(
                    o("Zor, çoğu zaman evet derim", "rel_a" to 2),
                    o("Öğrendim, net söylerim", "rel_b" to 2),
                    o("Kolay; seçici yaklaşırım", "rel_c" to 2),
                    o("Çalışıyorum, bazen zorlanırım", "rel_d" to 2),
                ),
            ),
            Question(
                "İlişkide en çok korktuğun şey?",
                listOf(
                    o("Kullanılmak / görülmemek", "rel_a" to 2),
                    o("Sınırımın çiğnenmesi", "rel_b" to 2),
                    o("Aşırı yakınlık baskısı", "rel_c" to 2),
                    o("Dengenin bozulması", "rel_d" to 2),
                ),
            ),
            Question(
                "Sevgi dilin hangisine yakın?",
                listOf(
                    o("Zaman ve ilgi harcamak", "rel_a" to 2),
                    o("Saygı ve öngörülebilirlik", "rel_b" to 2),
                    o("Derin ama kontrollü bağ", "rel_c" to 2),
                    o("Hep biraz karışık ama samimi", "rel_d" to 2),
                ),
            ),
            Question(
                "Bir ilişki dinamiğinde rolün?",
                listOf(
                    o("Besleyen", "rel_a" to 2),
                    o("Dengeleyen", "rel_b" to 2),
                    o("Gözlemleyen", "rel_c" to 2),
                    o("Ayar çeken", "rel_d" to 2),
                ),
            ),
            Question(
                "Son olarak: ‘fazla veriyorum’ dediğin oluyor mu?",
                listOf(
                    o("Evet, sık", "rel_a" to 2),
                    o("Eskiden evet, şimdi daha az", "rel_b" to 2),
                    o("Hayır, mesafem korur", "rel_c" to 2),
                    o("Bazen evet bazen hayır", "rel_d" to 2),
                ),
            ),
        )
        return Quiz(
            id = "quiz_relations_giving",
            title = "İlişkilerde fazla veren taraf mısın yoksa mesafeli mi?",
            subtitle = "Yakınlık stilin",
            categoryId = "cat_relations",
            tags = setOf(QuizTag.NEW),
            questions = qs,
            resultDefinitions = results,
            resultTieBreakOrder = order,
        )
    }

    private fun quizStressReaction(): Quiz {
        val results = listOf(
            QuizResultDefinition(
                id = "st_a",
                title = "Aksiyon modu",
                subtitle = "Stres = hareket",
                description = "Baskı artınca iş yaparsın, çözersin, yön değiştirirsin. Risk: bazen nefes almadan koşarsın; duraksamak da stratejidir.",
                shareTeaser = "Stres altında aksiyon moduna giriyormuşum. Gerçek Sen sonucu şaşırttı.",
                accent = ResultAccent.Amber,
            ),
            QuizResultDefinition(
                id = "st_b",
                title = "Soğuk kanlı analiz",
                subtitle = "Önce sistem, sonra duygu",
                description = "Panik yerine listeler, planlar, parçalarsın. İnsanlar yanında ‘krizde ayakta kalan’ tarafı görür.",
                shareTeaser = "Stres anında analiz modundaymışım. Quiz’i çöz, seninki ne?",
                accent = ResultAccent.Teal,
            ),
            QuizResultDefinition(
                id = "st_c",
                title = "İçe dönüş",
                subtitle = "Proses ederek toparlanma",
                description = "Önce iç sesini dinlersin; konuşmak veya yazmak sana iyi gelir. Dışarıdan ‘çekildi’ sanılabilir oysa işleyiş böyle.",
                shareTeaser = "Stresi içeride işliyormuşum. Bu quiz bunu net söyledi.",
                accent = ResultAccent.Violet,
            ),
            QuizResultDefinition(
                id = "st_d",
                title = "Destek arayan",
                subtitle = "Yalnız değilim, birlikte atlatırım",
                description = "Zor günde yakınlık istersin; paylaşmak yükü hafifletir. Bu güçlü bir strateji — ‘tek başına dayanmak’ şart değil.",
                shareTeaser = "Stres altında destek arayan taraftaymışım. Normal ve güçlüymüş aslında.",
                accent = ResultAccent.Coral,
            ),
        )
        val order = listOf("st_a", "st_b", "st_c", "st_d")
        val qs = listOf(
            Question(
                "Deadline yaklaştı; iç dünyan?",
                listOf(
                    o("Hızlanırım, elimi çabuk tutarım", "st_a" to 2),
                    o("Önceliklendiririm, sakin kalırım", "st_b" to 2),
                    o("Biraz içime çekilirim", "st_c" to 2),
                    o("Birine danışmak isterim", "st_d" to 2),
                ),
            ),
            Question(
                "Tartışma tırmandı; ilk tepki?",
                listOf(
                    o("Konuyu çözmeye odaklanırım", "st_a" to 2),
                    o("Nefes alıp mantık kurarım", "st_b" to 2),
                    o("Sessizleşir, düşünürüm", "st_c" to 2),
                    o("Arabulucu veya yakın biri isterim", "st_d" to 2),
                ),
            ),
            Question(
                "Plansız kötü haber; ne yaparsın?",
                listOf(
                    o("Hemen alternatif üretirim", "st_a" to 2),
                    o("Adımları sıraya koyarım", "st_b" to 2),
                    o("Kısa süre kendime kapanırım", "st_c" to 2),
                    o("Güvendiğim biriyle paylaşırım", "st_d" to 2),
                ),
            ),
            Question(
                "Uyku ve iştah stres altında?",
                listOf(
                    o("Koşturmaca artar, unuturum", "st_a" to 2),
                    o("Rutine sıkı sarılırım", "st_b" to 2),
                    o("Dalgalanır, iç ses yüksek", "st_c" to 2),
                    o("Yakınlık ararım, rahatlarım", "st_d" to 2),
                ),
            ),
            Question(
                "Stres sonrası toparlanma?",
                listOf(
                    o("Bir şeyleri bitirince rahatlarım", "st_a" to 2),
                    o("Kontrol listesi tamamlanınca", "st_b" to 2),
                    o("Zaman ve yalnızlık", "st_c" to 2),
                    o("Sohbet ve sarılma", "st_d" to 2),
                ),
            ),
            Question(
                "Kriz anında çevrendekiler seni nasıl tanımlar?",
                listOf(
                    o("İşi çözen", "st_a" to 2),
                    o("Sakin kalan", "st_b" to 2),
                    o("İçine kapanan ama dönen", "st_c" to 2),
                    o("Yanında insan isteyen", "st_d" to 2),
                ),
            ),
            Question(
                "Son soru: stres senin için çoğunlukla?",
                listOf(
                    o("Yakıt", "st_a" to 2),
                    o("Bulmaca", "st_b" to 2),
                    o("Fırtına", "st_c" to 2),
                    o("Paylaşılmayı bekleyen yük", "st_d" to 2),
                ),
            ),
        )
        return Quiz(
            id = "quiz_stress",
            title = "Stresli durumlarda nasıl tepki veriyorsun?",
            subtitle = "Krizdeki stilin",
            categoryId = "cat_truth",
            tags = setOf(QuizTag.MOST_SHARED),
            questions = qs,
            resultDefinitions = results,
            resultTieBreakOrder = order,
        )
    }

    private fun quizMostAnnoyingTrait(): Quiz {
        val results = listOf(
            QuizResultDefinition(
                id = "it_a",
                title = "Yardımsız düzeltici",
                subtitle = "Doğru bildiğini herkes duysun istiyor",
                description = "Küçük hataları görünce duramıyorsun; senin için netlik, karşı taraf için bazen ‘sürekli düzeltilme’ gibi geliyor. Niyetin iyi — ama bazen insanlar sadece duyulmak ister, düzeltilmek değil.",
                shareTeaser = "Gerçek Sen: en itici özelliğim ‘yardımsız düzeltici’ çıktı. Tahmin et bakalım seninki ne?",
                accent = ResultAccent.Teal,
            ),
            QuizResultDefinition(
                id = "it_b",
                title = "Gizli rekabetçi",
                subtitle = "Fark ettirmeden yarış modu",
                description = "Başarıyı seversin; bazen bu ‘ben de yaptım / ben daha kötü’ gibi hafif gölgelerle kendini gösterir. Kötü niyet değil, alışkanlık — ama yakın çevre bunu hissedebiliyor.",
                shareTeaser = "Quiz dedi ki gizli rekabetçiymişim. İtirazım var ama içimden bir ses…",
                accent = ResultAccent.Amber,
            ),
            QuizResultDefinition(
                id = "it_c",
                title = "Okunması zor kitap",
                subtitle = "Çok içeridesin, dışarı az sinyal",
                description = "Düşünüyorsun, sindiriyorsun, az konuşuyorsun. Senin için dinginlik; bazıları için ‘ne hissediyor acaba?’ yorgunluğu. Açık bir işaret bazen her şeyi kolaylaştırır.",
                shareTeaser = "‘Okunması zor kitap’ sonucu aldım. Sert ama biraz da haklı sayılır.",
                accent = ResultAccent.Violet,
            ),
            QuizResultDefinition(
                id = "it_d",
                title = "Ana karakter modu",
                subtitle = "Hikâye senin etrafında dönüyor gibi",
                description = "Hikâyeleri büyük anlatırsın, dramayı hissedersin — bazen odağı çektiğini fark etmeden. Eğlenceli olabilir; bazen de çevre ‘biraz nefes alalım’ der.",
                shareTeaser = "En itici özelliğim ‘ana karakter modu’ymuş. Paylaşmadan duramadım.",
                accent = ResultAccent.Coral,
            ),
        )
        val order = listOf("it_a", "it_b", "it_c", "it_d")
        val qs = listOf(
            Question(
                "Sohbette en çok hangisi seni tetikler?",
                listOf(
                    o("Yanlış bilgi geçtiğinde içim cız ediyor", "it_a" to 2, imageResId = R.drawable.quiz_option_placeholder),
                    o("Başkasının övülmesi garip hissettiriyor", "it_b" to 2, imageResId = R.drawable.quiz_option_placeholder),
                    o("Kalabalıkta konuşmak zor geliyor", "it_c" to 2, imageResId = R.drawable.quiz_option_placeholder),
                    o("Konu bana gelmeyince sıkılıyorum", "it_d" to 2, imageResId = R.drawable.quiz_option_placeholder),
                ),
            ),
            Question(
                "Biri hikâye anlatırken sen?",
                listOf(
                    o("Doğru kelimeyi bulup düzeltirim", "it_a" to 2),
                    o("Aklıma kendi versiyonum gelir", "it_b" to 2),
                    o("Dinlerim, az tepki veririm", "it_c" to 2),
                    o("Ben de benzer bir anımı eklerim", "it_d" to 2),
                ),
            ),
            Question(
                "Grup sohbetinde seni en çok hangisi yansıtır?",
                listOf(
                    o("‘Aslında şöyle’ diye atan mesajlar", "it_a" to 2),
                    o("Gösterişsiz ama içten içe kıyaslama", "it_b" to 2),
                    o("Uzun süre sessiz kalıp tek cümle", "it_c" to 2),
                    o("Konuyu kendi üzerime çeken espriler", "it_d" to 2),
                ),
            ),
            Question(
                "Eleştiri aldığında ilk iç ses?",
                listOf(
                    o("Haklılar mı diye teknik analiz", "it_a" to 2),
                    o("Ben de onların eksiğini bilirim", "it_b" to 2),
                    o("İçime kapanır, uzun sürer", "it_c" to 2),
                    o("Neden bana hep bana deniyor", "it_d" to 2),
                ),
            ),
            Question(
                "Yanlış anlaşıldığında?",
                listOf(
                    o("Net kanıt ve düzeltme paketi", "it_a" to 2),
                    o("Soğurum, mesafe koyarım", "it_b" to 2, "it_c" to 1),
                    o("Susarım, sonra içerde döner", "it_c" to 2),
                    o("Açıklarım; biraz dramatik olabilir", "it_d" to 2),
                ),
            ),
            Question(
                "İnsanlar senden en çok neyi ‘yorucu’ bulabilir (dürüstçe)?",
                listOf(
                    o("Her şeyin doğrusunu bilmek istemem", "it_a" to 2),
                    o("Göze çarpmayan rekabet", "it_b" to 2),
                    o("Ne düşündüğümü tahmin ettirmemek", "it_c" to 2),
                    o("Konunun büyümesi ve duygusal yoğunluk", "it_d" to 2),
                ),
            ),
            Question(
                "Özür dileme tarzın?",
                listOf(
                    o("Ama şunu da söylemeliyim diye uzar", "it_a" to 2),
                    o("Özür + savunma karışımı", "it_b" to 2, "it_a" to 1),
                    o("Kısa ve geç; içeride devam eder", "it_c" to 2),
                    o("Büyük sahne: özür ve açıklama şovu", "it_d" to 2),
                ),
            ),
            Question(
                "Son: ‘itici’ dediğinde hangisi daha yakın (mizahi)?",
                listOf(
                    o("Düzeltme refleksi", "it_a" to 2),
                    o("Gizli skor tutma", "it_b" to 2),
                    o("Sinyal vermeme", "it_c" to 2),
                    o("Spotlight çekme", "it_d" to 2),
                ),
            ),
        )
        return Quiz(
            id = "quiz_annoying_trait",
            title = "Senin en itici özelliğin ne?",
            subtitle = "Sert ama komik gerçekler",
            categoryId = "cat_truth",
            tags = setOf(QuizTag.NEW, QuizTag.POPULAR),
            questions = qs,
            resultDefinitions = results,
            resultTieBreakOrder = order,
        )
    }

    private fun quizHiddenStrength(): Quiz {
        val results = listOf(
            QuizResultDefinition(
                id = "hs_a",
                title = "Sessiz dayanıklılık",
                subtitle = "Çok şeyi kırmadan taşırsın",
                description = "Gürültüsüz ama inatçı bir iç gücün var. İnsanlar bazen fark etmez; kritik anlarda ‘hâlâ ayaktasın’ gerçeği ortaya çıkar.",
                shareTeaser = "Gizli gücüm sessiz dayanıklılıkmış. Gerçek Sen bunu söyledi.",
                accent = ResultAccent.Teal,
            ),
            QuizResultDefinition(
                id = "hs_b",
                title = "Sezgisel keskinlik",
                subtitle = "Okumadan anlarsın",
                description = "Detayı kaçırmadan ‘hisseder’sin. Kararlarında mantık kadar içgüdün de rol alır ve çoğu zaman isabet eder.",
                shareTeaser = "Güçlü yönüm sezgiymiş. Quiz sonucu paylaşılası.",
                accent = ResultAccent.Violet,
            ),
            QuizResultDefinition(
                id = "hs_c",
                title = "İnsanları açan merak",
                subtitle = "Soru sorarak güç verirsin",
                description = "Doğru soruyla insanlar kendini daha net görür. Bu nadiren övülür ama ilişkilerde büyüyen etki yaratır.",
                shareTeaser = "Gizli süper gücüm: merakla açmakmış. Dene: Gerçek Sen.",
                accent = ResultAccent.Coral,
            ),
            QuizResultDefinition(
                id = "hs_d",
                title = "Pratik yaratıcılık",
                subtitle = "Kısıtta çözüm üretirsin",
                description = "Kaynak azken en iyi fikirler senden çıkar. ‘Olur mu?’ dedirten ama işe yarayan yollar bulursun.",
                shareTeaser = "Kısıtta parlayan pratik yaratıcılıkmışım. Sonuç güzel.",
                accent = ResultAccent.Amber,
            ),
        )
        val order = listOf("hs_a", "hs_b", "hs_c", "hs_d")
        val qs = listOf(
            Question(
                "Zor bir günde seni ayakta tutan ne?",
                listOf(
                    o("İçten bir ‘devam’ sesi", "hs_a" to 2),
                    o("İçimdeki ‘bir şeyler ters’ uyarısı", "hs_b" to 2),
                    o("Anlam arayışı", "hs_c" to 2),
                    o("Bir çıkış yolu bulma isteği", "hs_d" to 2),
                ),
            ),
            Question(
                "Bir problem çıktı; güçlü olduğun an?",
                listOf(
                    o("Kimse görmeden sabrettiğim anlar", "hs_a" to 2),
                    o("İlk seferde doğru kokladığım an", "hs_b" to 2),
                    o("Birine doğru soruyu sorduğum an", "hs_c" to 2),
                    o("Elindekiyle yetinip çözdüğüm an", "hs_d" to 2),
                ),
            ),
            Question(
                "İnsanlar senden gizlice ne öğreniyor?",
                listOf(
                    o("Düşmeden yürümeyi", "hs_a" to 2),
                    o("İyi okumayı", "hs_b" to 2),
                    o("Kendini dinlemeyi", "hs_c" to 2),
                    o("Yaratıcı pratikliği", "hs_d" to 2),
                ),
            ),
            Question(
                "Takdir edilmediğini düşündüğün yetenek?",
                listOf(
                    o("Dayanıklılık", "hs_a" to 2),
                    o("Sezgi", "hs_b" to 2),
                    o("Derin dinleme / sorgulama", "hs_c" to 2),
                    o("Improvizasyon", "hs_d" to 2),
                ),
            ),
            Question(
                "Boş zamanında zihnin?",
                listOf(
                    o("Geçmişi sindirir, toparlarım", "hs_a" to 2),
                    o("Desenleri fark ederim", "hs_b" to 2),
                    o("Anlamlı şeyler okur/konuşurum", "hs_c" to 2),
                    o("Projeler ve fikirler üretirim", "hs_d" to 2),
                ),
            ),
            Question(
                "Birine güç vermek istesen nasıl yaparsın?",
                listOf(
                    o("Yanında olurum", "hs_a" to 2, "hs_c" to 1),
                    o("Doğru yolu gösteririm", "hs_b" to 2),
                    o("Kendini görmesini sağlarım", "hs_c" to 2),
                    o("Somut bir çözüm sunarım", "hs_d" to 2),
                ),
            ),
            Question(
                "Son: gizli güç dediğinde hangisi yakın?",
                listOf(
                    o("Sabır", "hs_a" to 2),
                    o("Sezgi", "hs_b" to 2),
                    o("Merak", "hs_c" to 2),
                    o("Buluşçuluk", "hs_d" to 2),
                ),
            ),
            Question(
                "Kendini hangi metaforla seversin?",
                listOf(
                    o("Sessiz kök", "hs_a" to 2),
                    o("İnce radar", "hs_b" to 2),
                    o("Açan soru", "hs_c" to 2),
                    o("Eldeki malzeme şef’i", "hs_d" to 2),
                ),
            ),
        )
        return Quiz(
            id = "quiz_hidden_strength",
            title = "Gizli güçlü yönün aslında ne?",
            subtitle = "Fark etmediğin süper güç",
            categoryId = "cat_strength",
            tags = setOf(QuizTag.POPULAR, QuizTag.NEW),
            questions = qs,
            resultDefinitions = results,
            resultTieBreakOrder = order,
        )
    }
}

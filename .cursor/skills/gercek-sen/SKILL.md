---
name: gercek-sen
description: >-
  Gerçek Sen Android (Kotlin + Compose) quiz uygulamasında çalışırken ürün ve kod
  öncelikleri: sade ana sayfa, akıcı quiz akışı, paylaşılabilir sonuç ekranı,
  minimal MVP, gereksiz karmaşadan kaçınma. Proje kökündeki bu repoda kullanılır.
---

# Gerçek Sen — proje ilkeleri

## Ne zaman uygula

Bu skill, `gercek-sen` reposunda UI, navigasyon, veri modeli veya yeni özellik eklerken geçerlidir.

## Ürün ve UX

- **Ana sayfa aşırı sade kalsın:** kullanıcı 3 saniye içinde kategori seçebilmeli; metin ve bölüm sayısını minimumda tut.
- **Quiz akışı hızlı ve akıcı olsun:** tek soru, büyük dokunma alanları, ilerleme net; gereksiz adım ve animasyon ekleme.
- **Sonuç ekranı screenshot ve paylaşıma uygun olsun:** güçlü başlık, kısa alt metin, okunaklı tipografi; “öğrenci projesi” veya boş şablon hissi verme.
- **Odak quiz deneyimi:** login, profil, bildirim, backend yoksa ekleme; her özellik için “bu gerçekten gerekli mi?” diye sor.

## Kod ve mimari

- **Genişlemeye uygun, minimal kal:** `QuizRepository` ve model katmanını koru; içerik büyüyünce JSON/assets eklenebilsin.
- **Over-engineering yapma:** gereksiz modül, DI framework veya ağır pattern ekleme; ViewModel + Compose yeterli.
- **material-icons-extended kullanma** (boyut/performans): mümkünse `material-icons-core`, emoji veya hafif vektör tercih et.
- **Tutarlı tema:** Material 3, sıcak palet, açık/koyu uyumu koru; yeni ekranlarda mevcut kart ve boşluk ritmini takip et.

## Paylaşım ve içerik

- Paylaşım metni kısa ve etkileyici olsun; `buildShareText` benzeri tek yerde üret.
- Quiz metinleri jenerik olmasın; sonuçlar paylaşılabilir ve birbirinden ayrılsın.

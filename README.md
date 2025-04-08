# Shop Main - Compose / Hilt / Mavericks 기반 이커머스 메인 데모

Android Compose 기반으로 제작된 **이커머스 메인 화면 데모 앱**입니다.  
네이티브 UI 기반으로 구성된 상품 리스트와 배너 등 다양한 섹션을 포함하며,  
상품 클릭 시 웹뷰로 이동하며 JavaScript와 연동되는 구조를 포함하고 있습니다.

---

## 데모 특징

- 이커머스 메인 화면 구성 (메인 배너, 상품 그리드, 이미지 그리드 등)
- 배너 Pager는 **Parallax 효과 + 자동 슬라이드**
- 상품 셔플 새로고침 `Alpha 애니메이션` 예시
- 상품 클릭 시 WebView로 이동 + JS 인터페이스 연동 예시

---

## 실행 환경

| 항목     | 설명                                                     |
|--------|--------------------------------------------------------|
| IDE    | Android Studio **Ladybug (2024.2.1)** 이상               |
| SDK    | compileSdk 35 / targetSdk 35                           |
| Gradle | Kotlin DSL / Version Catalog (`libs.versions.toml`) 사용 |

---

## 주요 기술

| 카테고리 | 라이브러리                            |
|------|----------------------------------|
| UI   | Jetpack Compose, Material3, Coil |
| DI   | Hilt                             |
| 아키텍처 | **Mavericks (MVI)**              |
| 네트워크 | Retrofit                         |
| 라우팅  | Compose Navigation               |
| 테스트  | JUnit, MockK, Compose UI Test    |

---

## Mavericks(MVI)

앱 전체 상태 관리를 **Mavericks의 MVI 구조**로 관리하고 있습니다.  
아래는 섹션 셔플 기능을 예시로 한 ViewModel 상태 처리입니다.

### ViewModel 상태 정의

```kotlin
data class HomeState(
    val sections: Async<List<HomeSectionModel>> = Uninitialized,
    val anchorGroupId: String? = null
) : MavericksState
```

### 상태 갱신 예시

```kotlin
fun shuffleGroup(sectionId: String) = setState {
    val list = sections() ?: return@setState this
    val updated = Success(
        list.map { section ->
            if (section.sectionId == sectionId) section.copy(shuffleKey = section.shuffleKey.inc()) else section
        }
    )
    copy(sections = updated)
}
```

---

## 주요 Screen

- **HomeScreen** – Native UI Section List 구성, ViewModel 상태 관리
- **DetailScreen** – WebView AndroidView 구성, JavaScriptInterface 연동

---

## 주요 Section UI

- **BannerSection** – 배너 영역 Pager, Parallax effect, 무한 스와이프, 자동 슬라이드
- **StyleGridSection** – 그리드 교차 스타일 구성 (좌/우 교차 반복)
- **ScrollSection** – 가로 스크롤 카드 리스트 + 새로고침 기능 포함
- **Header/Footer 컴포저블** – 타이틀, 아이콘, "더보기" 링크 클릭 시 이동

---

## WebView JavaScriptInterface

- 상품 클릭 시 WebView 화면으로 전환
- 웹 내의 `historyBack()` 호출을 통해 앱 내 `onBack()` 콜백을 트리거합니다.

```kotlin
class BrowserJSBridge(val onBack: () -> Unit) {
    @JavascriptInterface
    fun historyBack() {
        Log.e("BrowserJSBridge", "historyBack")
        onBack()
    }
}
```

---

## 테스트

- **Unit Test**: HomeViewModel 상태, 유즈케이스, 셔플 로직 테스트 등
  - HomeTest.kt
- **UI Test**: 로딩, 스크롤, 클릭, WebView 이동, 셔플 등 기능 UI 테스트
  - HomeViewModelTest.kt, SectionMapperTest.kt

---

## 라이선스

해당 프로젝트는 개인 포트폴리오 및 기술 테스트용으로 제작되었습니다.

---
KareerAI
│
├── build.gradle.kts (Project)
├── settings.gradle.kts
├── gradle.properties
├── local.properties
├── gradlew
├── gradlew.bat
├── README.md
├── PROJECT_STRUCTURE.md
├── ESTRUTURA.md
│
├── gradle/
│   └── libs.versions.toml
│
└── app/
    ├── build.gradle.kts (Module)
    ├── proguard-rules.pro
    └── src/
        └── main/
            ├── AndroidManifest.xml
            │
            ├── java/com/kiko/kareerai/
            │   ├── MainActivity.kt
            │   │
            │   ├── components/
            │   │   ├── alertdialog/
            │   │   │   └── AlertDialogKiko.kt
            │   │   ├── appbars/
            │   │   │   ├── AppBarKiko.kt
            │   │   │   └── BottomBarKiko.kt
            │   │   ├── buttons/
            │   │   │   ├── ButtonKiko.kt
            │   │   │   └── FabKiko.kt
            │   │   ├── cards/
            │   │   │   └── CardKiko.kt
            │   │   ├── divider/
            │   │   │   └── DividerKiko.kt
            │   │   ├── layout/
            │   │   │   ├── LayoutKiko.kt
            │   │   │   └── NavigationDrawerKiko.kt
            │   │   ├── outlined/
            │   │   │   ├── OutlinedKiko.kt
            │   │   │   └── TextFieldColor.kt
            │   │   ├── progress/
            │   │   │   └── ProgressKiko.kt
            │   │   ├── search/
            │   │   │   └── SearchKiko.kt
            │   │   ├── switch/
            │   │   │   └── SwitchKiko.kt
            │   │   └── toast/
            │   │       └── ToastKiko.kt
            │   │
            │   ├── data/
            │   │   ├── local/
            │   │   │   ├── dao/
            │   │   │   │   ├── CurriculoDao.kt
            │   │   │   │   ├── JobDao.kt
            │   │   │   │   └── UsuarioDao.kt
            │   │   │   ├── database/
            │   │   │   │   └── KareerAIDatabase.kt
            │   │   │   ├── datastore/
            │   │   │   │   └── ThemePreferences.kt
            │   │   │   └── entity/
            │   │   │       ├── Curriculo.kt
            │   │   │       ├── Job.kt
            │   │   │       └── Usuario.kt
            │   │   ├── model/
            │   │   │   └── Perfil.kt
            │   │   ├── remote/
            │   │   │   ├── ai/
            │   │   │   │   ├── AIRepository.kt
            │   │   │   │   ├── AIRequest.kt
            │   │   │   │   ├── AIResponse.kt
            │   │   │   │   └── AIService.kt
            │   │   │   ├── jobs/
            │   │   │   │   ├── JobRepository.kt
            │   │   │   │   ├── JobResponse.kt
            │   │   │   │   └── JobService.kt
            │   │   │   └── network/
            │   │   │       └── RetrofitClient.kt
            │   │   └── repository/
            │   │       ├── CurriculoRepository.kt
            │   │       └── UsuarioRepository.kt
            │   │
            │   ├── domain/
            │   │   └── usecase/
            │   │       ├── AnalisarCurriculoUseCase.kt
            │   │       ├── BuscarVagasUseCase.kt
            │   │       ├── DeletarVagaUseCase.kt
            │   │       ├── ObterVagasSalvasUseCase.kt
            │   │       └── SalvarVagaUseCase.kt
            │   │
            │   ├── navigation/
            │   │   ├── AppNavigation.kt
            │   │   └── Screen.kt
            │   │
            │   ├── ui/
            │   │   ├── screen/
            │   │   │   ├── curriculum/
            │   │   │   ├── forgot/
            │   │   │   ├── jobs/
            │   │   │   ├── login/
            │   │   │   ├── main/
            │   │   │   ├── register/
            │   │   │   ├── settings/
            │   │   │   └── splash/
            │   │   └── theme/
            │   │       ├── AppTheme.kt
            │   │       ├── Color.kt
            │   │       ├── Theme.kt
            │   │       └── ...
            │   │
            │   └── viewmodel/
            │       ├── CurriculumViewModel.kt
            │       ├── JobViewModel.kt
            │       └── ...
            │
            └── res/
                ├── drawable/
                ├── mipmap-*/
                ├── values/
                │   ├── colors.xml
                │   ├── strings.xml
                │   └── themes.xml
                └── xml/

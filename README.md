## Description
Application that displays current wifi status on screen when user toggles wifi connection on their phone

## To ensure status updates are immediate do either of the following

```
//=========== Option 1 - Call method in init block of viewmodel and collect it in UI==========

// In your VM

    init {
        checkMobileDataStatus()
    }

// In your UI
 val viewModel: WifiConnectionStatusViewModel = hiltViewModel()
 val state by viewModel.isMobileDataON.collectAsState()

 WifiConnectionStatusScreen(state)

// =========== Option 2 - Only call method in launched effect in UI===========
 val viewModel: WifiConnectionStatusViewModel = hiltViewModel()
 val state by viewModel.isMobileDataON.collectAsState()

  LaunchedEffect(Unit) {
    viewModel.checkMobileDataStatus()
}

````





Dev Proof

https://github.com/user-attachments/assets/fc1ca786-5084-4bde-87ca-78a4a155f2ed


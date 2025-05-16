import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        //comment the .keyboard to enable edge to edge on ios
        ComposeView()
                .ignoresSafeArea(/*.keyboard*/) // Compose has own keyboard handler
    }
}




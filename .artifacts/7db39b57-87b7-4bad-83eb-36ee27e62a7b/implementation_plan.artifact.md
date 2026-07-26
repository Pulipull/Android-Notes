# Implementation Plan - Fix Navigation Bug in About Screen

The goal is to fix the bug where clicking "Tentang Aplikasi" causes the app to exit to the home launcher instead of navigating to the About Screen. I will re-verify and adjust the navigation routes and ensure the `AboutScreen` rendering is robust.

## User Review Required

> [!IMPORTANT]
> I will change the navigation route for the About screen from `"about"` to `"about_screen"` to avoid any potential conflicts and update the resource usage in `AboutScreen` to be safer.

## Proposed Changes

### Navigation

#### [MODIFY] [Screen.kt](file:///C:/Users/A485/Mobile/10-MyNote/app/src/main/java/com/syaiful/mynotemaster/navigation/Screen.kt)
- Change `About` route to `"about_screen"`.

#### [MODIFY] [MyNoteNavGraph.kt](file:///C:/Users/A485/Mobile/10-MyNote/app/src/main/java/com/syaiful/mynotemaster/navigation/MyNoteNavGraph.kt)
- No changes needed if it uses `Screen.About.route`, but I will double-check.

### UI Screens

#### [MODIFY] [AboutScreen.kt](file:///C:/Users/A485/Mobile/10-MyNote/app/src/main/java/com/syaiful/mynotemaster/ui/screens/AboutScreen.kt)
- Use `R.drawable.ic_launcher_foreground` instead of `R.mipmap.ic_launcher` for better compatibility with the `Image` composable.
- Ensure the layout is as simple as possible to rule out rendering crashes.

## Verification Plan

### Automated Tests
- Run Gradle build to ensure no compilation errors.

### Manual Verification
- Open the app.
- Click the Overflow Menu (three dots).
- Click "Tentang Aplikasi".
- **EXPECTED**: The app navigates to the About screen.
- **EXPECTED**: Clicking the back button on the About screen returns to the Dashboard.
- **EXPECTED**: The app DOES NOT exit to the home launcher.

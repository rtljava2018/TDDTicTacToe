<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>TicTacToe – TDD Driven Kotlin/Compose Game</title>
</head>
<body>

  <h1>🕹️ TicTacToe – TDD Driven Kotlin/Compose Game</h1>

<h2>📖 Project Overview</h2>
  <p>
    This project is a <strong>TicTacToe game</strong> built with <strong>Kotlin, Jetpack Compose, and Hilt DI</strong>.
    It demonstrates <strong>clean architecture principles</strong>, <strong>test-driven development (TDD)</strong>, and <strong>state-driven UI</strong>.
  </p>
  <p>
    The game supports:
    <ul>
      <li>Two players (X and O).</li>
      <li>Win detection (rows, columns, diagonals).</li>
      <li>Draw detection.</li>
      <li>Reset functionality.</li>
      <li>Reactive UI updates via <code>StateFlow</code>.</li>
    </ul>
  </p>

<h2>🚀 How to Clone &amp; Run</h2>
  <pre>
# Clone the repository
git clone https://github.com/rtllabs/tdd-tictactoe.git

# Navigate into the project
cd tdd-tictactoe

# Build the project
./gradlew build

# Run unit tests
./gradlew test

# Run instrumentation (UI) tests
./gradlew connectedAndroidTest
  </pre>

<h2>🎮 How to Play</h2>
  <ol>
    <li>Launch the app on an emulator or device.</li>
    <li>The game starts with <strong>Player X</strong>.</li>
    <li>Tap on any empty cell to place your mark.</li>
    <li>Players alternate turns until:
      <ul>
        <li>One player wins (row, column, diagonal).</li>
        <li>Or the board fills with no winner (draw).</li>
      </ul>
    </li>
    <li>Use the <strong>Reset</strong> button to start a new game.</li>
  </ol>

<h2>⚙️ Tech Stack</h2>
  <ul>
    <li><strong>Language</strong>: Kotlin</li>
    <li><strong>UI</strong>: Jetpack Compose</li>
    <li><strong>DI</strong>: Hilt</li>
    <li><strong>Architecture</strong>: Clean Architecture + MVVM</li>
    <li><strong>State Management</strong>: StateFlow + sealed <code>GameUiState</code></li>
    <li><strong>Testing</strong>:
      <ul>
        <li>JUnit (unit tests)</li>
        <li>AndroidX Compose UI Test </li>
      </ul>
    </li>
  </ul>

<h2>🛠️ Gradle Version</h2>
  <ul>
    <li><strong>Gradle Wrapper</strong>: 8.13</li>
    <li><strong>Android Gradle Plugin (AGP)</strong>: 8.13.1</li>
    <li><strong>Kotlin</strong>: 2.2.21</li>
  </ul>

<h2>✅ TDD Test Coverage</h2>

<h3>Unit Tests (<code>src/test/java</code>)</h3>
  <ul>
    <li><strong>MakeMoveUseCaseTest</strong>:
      <ul>
        <li>Updated game state after moves.</li>
        <li>Prevents moves on occupied cells.</li>
        <li>Detects row, column, diagonal wins.</li>
        <li>Detects draw state.</li>
      </ul>
    </li>
  </ul>

<h3>Integration Tests (<code>src/androidTest/java</code>)</h3>
  <ul>
    <li><strong>GameScreenIntegrationTest</strong>:
      <ul>
        <li>Initial state shows <code>"Current Player: X"</code>.</li>
        <li>Clicking a cell updates the board.</li>
        <li>Row win shows <code>"Winner: X"</code>.</li>
        <li>Draw shows <code>"It's a Draw!"</code>.</li>
        <li>Reset returns to initial state.</li>
      </ul>
    </li>
  </ul>

  <p>
    Together, these tests ensure <strong>line-by-line coverage of domain logic</strong> and <strong>UI correctness under real Hilt DI</strong>.
  </p>

<h2>📊 Summary</h2>
  <p>
    This project is both a <strong>learning kata</strong> and a <strong>production-ready scaffold</strong> for building modular, testable Android apps.  
    It showcases:
    <ul>
      <li><strong>TDD discipline</strong> (domain logic fully covered).</li>
      <li><strong>Composable UI testing</strong> (asserting text and board updates).</li>
      <li><strong>Hilt DI integration</strong> (real dependencies injected in tests).</li>
    </ul>
  </p>

</body>
</html>

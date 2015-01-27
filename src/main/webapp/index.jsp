
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>Todo List</title>
  <link rel="stylesheet" href="todo.css"/>
  <script type="text/javascript" src="jquery.js">
  
  </script>
  <script type="text/javascript" src="todo.js"> </script>
</head>

<body>

  <div id="todoapp">

    <header>
      <h1>Todos</h1> 
      <div id="stage"></div>
      <input id="new-todo" type="text" placeholder="What needs to be done?">
    </header>

    <section id="main">
      <input id="toggle-all" type="checkbox">
      <label for="toggle-all">Mark all as complete</label>
      <ul id="todo-list"></ul>
    </section>

    <footer>
      <a id="clear-completed"></a>
      <div id="todo-count"></div>
    </footer>

  </div>

  <div id="instructions">
    Double-click to edit a todo.
  </div>

  <div id="credits">
    Created by
    <br />
    <a href="http://jgn.me/">J&eacute;r&ocirc;me Gravel-Niquet</a>.
    <br />Rewritten by: <a href="http://addyosmani.github.com/todomvc">TodoMVC</a>.
  </div>
  </body>
</html>

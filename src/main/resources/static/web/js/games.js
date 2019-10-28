var app = new Vue({
  el: "#app",
  data: {
    gamesList: [],
    leaderboard: [],
    currentUser: "Guest",
    signupForm: false,
  },


  methods: {
    formatDate: function(created) {
      let input = new Date(created)
      let formated = input.toLocaleString();
      return formated;
    },
    login: function(user, pass) {
      $.post("/api/login", { user, pass })
        .done(function() {
          updateLists();
        })
        .fail(function() {
          alert("Login error");
        })
    },
    logout: function() {
      $.post("/api/logout")
        .done(function() {
          window.location = "/web/games.html";
        })
    },
    toggleSignup: function() {
      this.signupForm = !this.signupForm;
    },
    signUp: function(user, pass, confirm) {
      if (pass != confirm) {
        return alert("Passwords don't match");
      }
      $.post("/api/players", { user: user, pass: pass })
        .done(function() {
          app.login(user, pass)
        })
    },
    createGame: function() {
      $.post("/api/games")
        .done(function(data) {
          window.location = "/web/game.html?gp=" + data.gpid;
        })
        .fail(function(data) {
          alert("Something failed! Error: " + data.responseJSON.error)
        })
    },
    joinGame: function(game) {
      $.post("/api/games/" + game + "/players")
        .done(function(data) {
          window.location = "/web/game.html?gp=" + data.gpid;
        })
        .fail(function(data) {
          alert("Something failed! Error: " + data.responseJSON.error)
        })
    },
    joinButton: function(juego) {
      var containsPlayer = false;
      var playersInGame = 0;
      juego.players.forEach(player => {
        playersInGame++;
        if (player.name == app.currentUser) {
          containsPlayer = true;
        }
      });
      if (containsPlayer || this.currentUser == "Guest" || playersInGame > 1) {
        return false;
      } else {
        return true;
      }
    },
    enterGame: function(game) {
      $.get("/api/games/" + game + "/players")
        .done(function(data) {
          var filtered = data.players.filter(player => player.name == app.currentUser);
          window.location = "/web/game.html?gp=" + filtered[0].gpid;
        })
        .fail(function(data) {
          alert("Something failed! Error: " + data.responseJSON.error)
        })
    },
    reEnterButton: function(juego) {
      var containsPlayer = false;
      juego.players.forEach(player => {
        if (player.name == app.currentUser) {
          containsPlayer = true;
        }
      });
      if (containsPlayer && this.currentUser != "Guest") {
        return true;
      } else {
        return false;
      }
    }
  },
});

// ******************** FUNCIONES AUXILIARES ********************
// **************************************************************

// ******************** HEADERS PARA EL FETCH ********************
function fetchHeaders(method, data, mode, cache) {
  headers = method == "POST" ? {
    method: method,
    body: JSON.stringify(data),
    mode: mode,
    cache: cache
  } : {
    method: method,
    mode: mode,
    cache: cache
  };
  return headers;
};

// ******************** REALIZA EL FETCH Y NO METE EN UN ARRAY DE LA APP ********************
function fetchMyData(url, headers, dataHolder) {
  let promise = fetch(url, headers);
  promise
    .then(response => response.json())
    .then(myJson => {
      app[dataHolder] = myJson;
      if (dataHolder == "gamesList") { app.currentUser = myJson.player.name ? myJson.player.name : myJson.player; }
    })
    .catch(err => { alert(`Couldn't retrieve info. please, check this error: ${err}`) });
}

// ******************** LLAMADO AL FETCH ********************
function updateLists() {
  fetchMyData("/api/leaderboard", fetchHeaders("GET", "", "no-cors", "default"), "leaderboard");
  fetchMyData("/api/games", fetchHeaders("GET", "", "no-cors", "default"), "gamesList");
}

// ******************** ACTUALIZAR LAS LISTAS ********************
updateLists();

$('#dropdown-login').click(function(e) {
  e.stopPropagation();
});
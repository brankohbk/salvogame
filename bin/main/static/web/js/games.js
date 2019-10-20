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
          updateLists();
        })
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

  },
  computed: {
    userIcon: function() {
      if (this.currentUser == "") {
        return "far fa-user";
      } else {
        return "fas fa-user";
      }
    }
  }
});

function promiseModifiers(method, data, mode, cache) {

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


function fetchMyData(url, headers, dataHolder) {
  let promise = fetch(url, headers);
  promise
    .then(response => response.json())
    .then(myJson => {
      app[dataHolder] = myJson;
      if (dataHolder == "gamesList") { app.currentUser = myJson.player.name ? myJson.player.name : myJson.player; }
    })
    .catch(err => { console.log(`Couldn't retrieve info. please, check this error: ${err}`) });
}

function updateLists() {
  fetchMyData("/api/leaderboard", promiseModifiers("GET", "", "no-cors", "default"), "leaderboard");
  fetchMyData("/api/games", promiseModifiers("GET", "", "no-cors", "default"), "gamesList");
}

updateLists();
$('#dropdown-login').click(function(e) {
  e.stopPropagation();
});
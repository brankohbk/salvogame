var app = new Vue({
  el: "#app",
  data: {
    gamesList: [],
    leaderboard: [],
    currentUser: "",

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
          app.currentUser = user;
          console.log("logged in!");
        })
        .fail(function() {
          alert("error");
        })

    },
    logout: function() {
      $.post("/api/logout").done(function() {
        app.currentUser = "";
        console.log("logged out");
      })

    }

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
    })
    .catch(err => { `Couldn't retrieve info. please, check this error: ${err}` });
}

fetchMyData("/api/games", promiseModifiers("GET", "", "no-cors", "default"), "gamesList");
fetchMyData("/api/leaderboard", promiseModifiers("GET", "", "no-cors", "default"), "leaderboard");
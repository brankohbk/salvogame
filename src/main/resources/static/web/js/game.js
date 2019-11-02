var letras = ["", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"];
var currentPlayer = {};
var oponent = {};

//función a la que le paso elemento, por ej letras[0]
function createTableCell(table, element) {
  var html = "";
  if (element == "") {
    for (let i = 0; i < 11; i++) {
      if (i === 0) {
        html += '<div class="divTableCell black" id="' + table + element + i + '"></div>';
      } else {
        html += '<div class="divTableCell black" id="' + table + element + i + '">' + i + '</div>';
      }
    }
  } else {
    for (let i = 0; i < 11; i++) {
      if (i === 0) {
        html += '<div class="divTableCell black" id="' + table + element + i + '">' + element[0] + '</div>';
      } else {
        html += '<div class="divTableCell water" id="' + table + element + i + '"></div>';
      }
    }
  }
  return html;
}

function createHtml(table, array) {
  var html = "";
  for (let index = 0; index < array.length; index++) {
    html += '<div class="divTableRow">';
    html += createTableCell(table, array[index]);
    html += '</div>';
  }
  return html;
}
//creo la grilla
document.getElementById('myShips').innerHTML = createHtml("ships", letras);
document.getElementById('mySalvoes').innerHTML = createHtml("salvoes", letras);

//funcion que me devuelve el objeto del parámetro de la url (del ebook)
function paramObj(search) {
  var obj = {};
  var reg = /(?:[?&]([^?&#=]+)(?:=([^&#]*))?)(?:#.*)?/g;
  search.replace(reg, function(match, param, val) {
    obj[decodeURIComponent(param)] = val === undefined ? "" : decodeURIComponent(val);
  });
  return obj;
}

//pinto los ships del gp en la grilla
function showShips(json) {
  var ships = json.ships;
  ships.map(ship => {
    ship.shipLocation.map(loc => {
      var cell = "ships" + loc.toLowerCase();
      document.getElementById(cell).classList.add("shipPart")
    })
  });
}

//Pinto mis salvos
function mySalvoes(json) {
  var salvoes = json.salvoes;
  salvoes.filter(salvo => salvo.player == currentPlayer.id).map(salvo => {
    salvo.salvoLocation.map(loc => {
      var cell = document.getElementById("salvoes" + loc.toLowerCase());
      cell.innerHTML = salvo.turn;
      cell.classList.add("mySalvo");
    })
  })
}

//Pinto los salvos enemigos
function enemySalvoes(json) {
  var salvoes = json.salvoes;
  salvoes.filter(el => el.player == oponent.id)
    .map(salvo => {
      salvo.salvoLocation.map(loc => {
        var cell = document.getElementById("ships" + loc.toLowerCase());
        if (cell.classList.contains("shipPart")) {
          cell.innerHTML = salvo.turn;
          cell.classList.add("hitted");
        }

      })
    })
}

//Fetch de datos
function fetchShips() {
  $.post('/api/game_view/' + paramObj(location.search).gp)
    .done(function(data) {
      json = data.data
      json.gamePlayers.map(gamePlayer => {
        if (gamePlayer.gpid == paramObj(location.search).gp) {
          currentPlayer = gamePlayer;
          document.getElementById("gp1").innerHTML = currentPlayer.name + "(you)";
        } else {
          oponent = gamePlayer;
          document.getElementById("gp2").innerHTML = oponent.name
        }
        document.getElementById("vs").innerHTML = " vs ";
        showShips(json);
        mySalvoes(json);
        enemySalvoes(json);
        outSalvoes = json.salvoes;
      })
    })
    .fail(function(data) {
      console.log(data)
      alert("Something failed! Error: " + data.responseJSON.error)
    })
}
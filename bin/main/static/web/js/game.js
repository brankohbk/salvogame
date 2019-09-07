var letras = ["", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"];

//función a la que le paso elemento, por ej letras[0]
function createTableCell(element) {
  var html = "";
  if (element == "") {
    for (let i = 0; i < 11; i++) {
      if (i === 0) {
        html += '<div class="divTableCell black" id="' + element + i + '"></div>';
      } else {
        html += '<div class="divTableCell black" id="' + element + i + '">' + i + '</div>';
      }
    }
  } else {
    for (let i = 0; i < 11; i++) {
      if (i === 0) {
        html += '<div class="divTableCell black" id="' + element + i + '">' + element[0] + '</div>';
      } else {
        html += '<div class="divTableCell water" id="' + element + i + '"></div>';
      }
    }
  }
  return html;
}

function createHtml(array) {
  var html = "";
  for (let index = 0; index < array.length; index++) {
    html += '<div class="divTableRow">';
    html += createTableCell(array[index]);
    html += '</div>';
  }
  return html;
}
//creo la grilla
document.getElementById('divTableBody').innerHTML = createHtml(letras);

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
    ship.shipsLocation.map(loc => {
      document.getElementById(loc.toLowerCase()).style.background = "green";
      document.getElementById(loc.toLowerCase()).classList.add("shipPart")
    })
  });
}

//Pinto los salvos
funcion showSalvoes(json){
    var salvoes = json.salvoes;
    salvoes.map(salvo =>{
        salvo.salvoLocation.map(loc => {
        cell= document.getElementById(loc.toLowerCase());
        if (cell.classList.contains("shipPart")){
            cell.innerHTML = salvo.turn;
        }

        })
    })
}

//hago el fetch
fetchJson('/api/game_view/' + paramObj(location.search).gp).then(function(json) {
  document.getElementById("vs").innerHTML = " vs ";
  showShips(json);
  json.gamePlayers.map(function(e) {
    if (e.id == paramObj(location.search).gp) {
      document.getElementById("gp1").innerHTML = e.player.email + "(you)";
    } else {
      document.getElementById("gp2").innerHTML = e.player.email
    }
  });
}).catch(function(error) { console.log("Request failed: " + error.message); });
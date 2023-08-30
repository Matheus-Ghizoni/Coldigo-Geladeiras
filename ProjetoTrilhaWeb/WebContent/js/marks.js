COLDIGO.marca = new Object();

$(document).ready(function() {

	COLDIGO.marca.cadastrar = function() {
		var marca = new Object();
		marca.nome = document.frmAddMarca.nome.value;

		if (marca.nome == "") {
			COLDIGO.exibirAviso("Preencha todos os campos!");
		} else {
			$.ajax({
				type: "POST",
				url: COLDIGO.PATH + "marca/inserir",
				data: JSON.stringify(marca),
				success: function(msg) {
					COLDIGO.exibirAviso(msg);
					$("#addMarca").trigger("reset");
				},
				error: function(info) {
					COLDIGO.exibirAviso("Erro ao cadastrar uma nova marca: " + info.status + " - " + info.statusText);
				}
			});

		}
	}

	COLDIGO.marca.buscar = function() {
		var valorBusca = $("#campoBuscaMarca").val();
		$.ajax({
			type: "GET",
			url: COLDIGO.PATH + "marca/busca",
			data: "valorBusca=" + valorBusca,
			success: function(dados) {
				dados = JSON.parse(dados);
				console.log(dados);
				
				$("#listaMarcas").html(COLDIGO.marca.exibir(dados));

			},
			error: function(info) {
				COLDIGO.exibirAviso("Erro ao consultar os contatos: " + info.status + " - " + info.statusText);
			}
		});

	};

	COLDIGO.marca.exibir = function(listaDeMarcas) {	

		var tabela = "<table>" +
			"<tr>" +
			"<th>Nome da Marca</th>" +
			"<th class='acoes'>Ações</th>" +
			"</tr>";

		if (listaDeMarcas != undefined && listaDeMarcas.length > 0) {

			for (var i = 0; i < listaDeMarcas.length; i++) {
				tabela += "<tr>" +
					"<td>" + listaDeMarcas[i].nome + "</td>" +
					"<td>" +
					"<a onclick=\"COLDIGO.marca.excluir('"+listaDeMarcas[i].id+"')\"><img src='../../imgs/lixeira.svg' alt='Excluir registro'></a>"
					if (listaDeMarcas[i].status == 1){
						tabela += "<label class='switch'>" +
  						"<input onclick=\"COLDIGO.marca.inativar('"+listaDeMarcas[i].id+"')\" id='statusMarca' type='checkbox' checked>" +
  						"<span class='slider round'></span>" +
						"</label>"
					} else {
						tabela += "<label class='switch'>" +
  						"<input onclick=\"COLDIGO.marca.inativar('"+listaDeMarcas[i].id+"')\" id='statusMarca' type='checkbox'>" +
  						"<span class='slider round'></span>" +
						"</label>"
					}
					tabela +=
					"</td>" +
					"</tr>"
					
			}

		} else if (listaDeMarcas == "") {
			tabela += "<tr><td colspan='6'>Nenhum registro encontrado</td></tr>";
		}
		tabela += "</table>";

		return tabela;
	};
	
	COLDIGO.marca.buscar();
	
	COLDIGO.marca.excluir = function(id) {
		$.ajax({
			type: "DELETE",
			url: COLDIGO.PATH + "marca/excluir/" + id,
			success: function(msg) {
				COLDIGO.exibirAviso(msg);
				COLDIGO.marca.buscar();
			},
			error: function(info) {
				produtos = COLDIGO.marca.buscarProdutos(id);
				if (produtos == undefined || produtos == ''){
					COLDIGO.exibirAviso("Erro ao excluir marca: " + info.status + " - " + info.statusText);
				} else {
					COLDIGO.exibirAviso("Erro ao excluir marca: Existe produtos cadastrados com essa marca! " + info.status + " - " + info.statusText);
				}
				
			}
		});
	};
	
	COLDIGO.marca.buscarProdutos = function(id) {
		
		$.ajax({
			type: "GET",
			url: COLDIGO.PATH + "marca/buscarProdutos",
			data: "valorBusca=" + id,
			success: function(dados) {
				dados = JSON.parse(dados);
				return dados;
			},
			error: function(info) {
				COLDIGO.exibirAviso("Erro ao consultar" + info.status + " - " + info.statusText);
			}
		});

	};
	
	COLDIGO.marca.inativar = function(id){

		$.ajax({
			type: "PUT",
			url: COLDIGO.PATH + "marca/alterar/" + id,
			success: function(msg){
				COLDIGO.exibirAviso(msg);
			},
			error: function(info){
				COLDIGO.exibirAviso("Erro alterar status da marca: "+ info.status + " - " + info.statusText);
				
			}
		});
		
		
	}
	
	
});

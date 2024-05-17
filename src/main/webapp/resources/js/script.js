function gerarPdf() {
	var pegarDados = document.getElementById('content').innerHTML;
	
	let estilo = '<style>';
	estilo += '.conteudo-div { padding: 20px 50px 20px 50px; font-size: 18px; text-align: justify; }'
	estilo += '</style>';

	var janela = window.open('https://seguranca.bb.com.br', '', 'left=100,top=100,width=1200,height=800');
	janela.document.write(`<?xml version="1.0" encoding="UTF-8" ?>
		<!DOCTYPE html>
		<html xmlns="http://www.w3.org/1999/xhtml"
			xmlns:h="http://xmlns.jcp.org/jsf/html"
			xmlns:f="http://xmlns.jcp.org/jsf/core"
			xmlns:p="http://primefaces.org/ui" lang="pt-br">`);
	janela.document.write('<head>');
	janela.document.write('<title>Atas do comitê</title>');
	janela.document.write(estilo);
	janela.document.write('</head>');
	janela.document.write('<body>');
	janela.document.write(pegarDados);
	janela.document.write('</body>');
	janela.document.write('</html>');
	janela.document.close();
	janela.print();	
}
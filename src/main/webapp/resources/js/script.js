function gerarPdf() {
	var pegarDados = document.getElementById('content').innerHTML;
	
	let estilo = '<style>';
	estilo += '.conteudo-div { padding: 20px 50px 20px 50px; font-size: 18px; text-align: justify; }'
	estilo += '</style>';

	var janela = window.open('https://seguranca.bb.com.br', '', 'left=100,top=100,width=1200,height=800');
	janela.document.write('<html>');
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
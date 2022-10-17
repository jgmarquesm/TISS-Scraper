# TISS-Scraper
Web Scraping para coleta de dados da TISS

Web Scraper feito em Groovy 3.0.11 usando as bibiotecas <a href="https://jsoup.org/">JSoup</a>, <a href="https://http-builder-ng.github.io/http-builder-ng/index.html">HttpBuilder-NG</a>, <a href="https://jakarta.ee/specifications/mail/2.0/jakarta-mail-spec-2.0.html">Jakarta Mail</a> e CRUD de nome e email na lista de envios, usando o DB <a href="https://www.postgresql.org/">PostgreSQL</a> .

Projeto desenvolvido com o intuito de coletar 3 tipos dados da <a href="https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss">Troca de Informações na Saúde Suplementar (TISS)</a>.

Dados coletados (<a href="https://github.com/jgmarquesm/TISS-Scraper/tree/main/Downloads">Clique aqui</a>):

- Download de um .zip com o Padrão TISS - Identificação da versão vigente - Componentes de Comunicação;
- Informações da tabela de Histórico das versões anteriores - salvos em um .csv;
- Download de um .xlsx da Tabela de Erros no envio para a ANS.

<img width=100% height=25px src="https://user-images.githubusercontent.com/111225477/282803485-8d33252d-d61d-4b2f-b1b2-1948808b9c0f.png"/>
<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=c1c701&height=120&section=header"/>

# Teste Técnico - LETRAS.com 

## Resumo 
Este projeto trata-se de um teste técnico da empresa [LETRAS.com](https://www.letras.mus.br/) no qual o objetivo era construir um App de jogo da velha que permita o usuário decidir se quer jogar contra outro jogador ou contra um BOT. Além disso, o usuário pode escolher o tamanho do tabuleiro entre 3x3, 4x4, 5x5 ou 6x6.

## Imagens do App

<div align="center"> 
<img src = "https://user-images.githubusercontent.com/111225477/282784637-6a94b55f-009f-4e62-9589-75f8137c54f0.png" width = "150px" >
<img src = "https://user-images.githubusercontent.com/111225477/282784649-d4c488e2-d73d-475e-bfee-c4a2dca618d6.png" width = "150px" >
<img src = "https://user-images.githubusercontent.com/111225477/282784665-bd3d36a3-6ae6-4409-918c-332399870b25.png" width = "150px" >
<img src = "https://user-images.githubusercontent.com/111225477/282784677-9fdf3ec4-504b-4371-a31b-5fd38cd8dc0c.png" width = "150px" >
</div>


</br>

# Requisitos exigidos pela LETRAS.com

## Tela inicial 

- Um botão para selecionar o ***tipo de jogo***: "Jogar contra outra pessoa" ou "Jogar contra o BOT".
- Dois campos para inserir o ***nome dos jogadores***. Apenas o primeiro deve ser inserido (não aceitar espaços). Se você escolher - jogar contra o robô, o segundo jogador deve ser chamado de "Robô", e o campo do segundo jogador deve ser desativado para edição.
- O nome do Jogador 1 deve persistir entre as execuções do aplicativo
- Um seletor de tamanho do tabuleiro que permite escolher entre 3x3 até 10x10, sempre com um formato quadrado.
- Um botão ***"Começar o jogo"***.
- Um botão ***"Ver histórico de jogadas"***.

## Tela do jogo 

- O tabuleiro do jogo, que deve corresponder ao tamanho selecionado na tela inicial.
- Informação sobre de quem é a vez de jogar ***(Jogador 1, Jogador 2 ou Robô)***.
- Ao finalizar o jogo, exibir quem ganhou e dois botões: um para ***"Repetir o jogo"*** e outro para ***"Voltar"***.

## Tela de Histórico
  - Uma lista em ordem decrescente de jogadas, mostrando o resultado das partidas com os nomes dos jogadores e o vencedor.
 - O histórico de partidas deve persistir entre as execuções do aplicativo.

 </br>

# Detalhes do desenvolvimento do App

Este App foi totalmente escrito na linguagem Kotlin, através da plataforma Android Studio. Apesar de saber usar o framework moderno, [Jetpack Compose](https://developer.android.com/jetpack/compose/documentation?hl=pt-br), para criação das telas de UI, decidi escrevê-lo em XML para melhorar minhas habilidades nesse aspecto de criação de telas.

Para fazer o cadastro e busca de usuários localmente foi usada a biblioteca de persistência de dados locais no smartphone, o [ROOM](https://developer.android.com/training/data-storage/room?hl=pt-br) que cria um banco de dados SQLite no aparelho do usuário permitindo a utilização de um sistema CRUD (Create, Read, Update e Delete)     

Também tentei deixar o uso do app pelo usuário mais flúido através de [animações](https://developer.android.com/guide/topics/resources/animation-resource?hl=pt-br)/transições de telas e objetos como por exemplo: 
A cada jogada uma peça parece surgir de dentro do tabuleiro de uma forma suave. Ao clicar em "Novo jogo" o tabuleiro é limpo em forma de cascata removendo peça por peça ao invés de apenas apagar todas de uma vez.

<!-- ## Video demonstrativo

A seguir temos um GIF  que demonstra de forma resumida das funcionalidades do App. Para acessar o vídeo completo com audio, basta clicar no link ao lado: 
 [Link do vídeo](https://youtu.be/z76hwPDHbc8)

<div align="center">
<img src = "https://user-images.githubusercontent.com/111225477/256329145-55d55350-f248-459e-b895-95f565cbee20.gif" width = "230px">

</div>
</br> -->

## Pontos Bônus especificados pela LETRAS.com

- No histórico, exibir um ranking de vitórias por nome de jogador, ignorando letras maiúsculas/minúsculas e acentos.   <span style="color: green;"> ***ALCANÇADO*** </span>

- Ao jogar contra a máquina: Garanta que o robô não permita que o jogador ganhe caso o jogador possa vencer na próxima jogada. <span style="color: green;"> ***ALCANÇADO*** </span>
- Certifique-se de que o robô ganhe quando possível em sua jogada. ***EM PRODUÇÃO***
- Implemente jogadas "inteligentes" para o robô, fazendo com que ele escolha locais próximos aos locais que já escolheu, em vez de escolher aleatoriamente. ***EM PRODUÇÃO***


<!-- <div align="center"> 
<img src = "https://user-images.githubusercontent.com/111225477/255669769-5d9c828a-dcb0-4a14-ac89-e36351b85814.png" width = "650px" >
</div> -->


## Sugestões de Melhoria no App
Abaixo listarei alguns pontos em que na minha opinião podem tornar o App mais sofisticado e mais bem apresentado para o usuário final. Estou trabalhando nesses pontos atualmente mas como a data de entrega do projeto foi 11/11/23 (4 dias para conclusão) eu decidi ***priorizar a entrega*** mas aprimorar o app posteriormente, vamos aos pontos:

- Ensinar o BOT a fazer ***"Ataques"*** também a fim de ganhar o jogo quando possível. Acredito que não terei dificuldades visto que o jogo já tem uma lógica pronta para fazer defesa.

- ***Implementar sons*** a cada jogada para simular uma peça sendo colocada no tabuleiro.

- Permitir ***login com Facebook e Google***. Já tenho uma noção de como fazer isso usando a biblioteca do [Firebase Authentication](https://firebase.google.com/docs/auth/android/facebook-login?hl=pt-br) que eu já usei para login básico (email e senha) mas que também permite logins a partir de redes sociais.
- Permitir salvamento de dados em uma Api para criar um ranking com maior volume de jogadores. Esse ponto seria beem complexo por se tratar de BackEnd que eu ainda não tenho tanta exeriência. Seria uma oportunidade de aprender algumas skills de backend usando o próprio [Java](https://docs.oracle.com/en/java/) ou [Kotlin](https://kotlinlang.org/docs/server-overview.html) que eu já tenho maior contato/experiência.


## Agradecimentos
Gostaria de agradecer primeiramente à ***Equipe de RH do LETRAS.com***  pois eu havia sido eliminado automaticamente pela gupy por morar em Nova Iguaçu/RJ e a vaga ser presencial em Belo Horizonte/MG . Mas após um email enviado por mim, mostrando que tenho capacidade de auxiliar na resolução de problemas da empresa [LETRAS.com](https://www.letras.mus.br/), eles decidiram reconsiderar minha participação no processo seletivo permitindo que eu avançasse para a etapa de testes online. Fui aprovado nessa etapa e por isso tive a oportunidade de participar da criação deste App Jogo da Velha. 

Fico grato por estar participando do processo seletivo para Desenvolvedor Android na empresa [LETRAS.com](https://www.letras.mus.br/), esse teste foi bastante desafiador e divertido, exigindo-me conhecimentos e formas de solucionar problemas aparentemente simples mas que na prática podem demandar diversos conhecimentos específicos.

Diversas vezes me deparei com problemas complexos que exigiram muita ***criatividade*** de minha parte para propor uma solução válida.

Estou empolgado com a hipótese de poder trabalhar e colaborar com uma empresa de alta tecnologia e ganhadora de diversos prêmios como: [GPTW](https://gptw.com.br/) (2020) e [Lugares Incríveis para Trabalhar 2021](https://economia.uol.com.br/reportagens-especiais/lugares-incriveis-para-trabalhar-2021/#page17). 😃
 
 <img src = "https://akamai.sscdn.co/letras/common/static/img/work/main.vf7ba30cf.png" width = "100%" >

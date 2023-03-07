/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Nicola
 * Created: 14-mag-2018
 */

/*
    private int id;
    private String nome;
    private String cognome;
    private String username;
    private String email;
    private String password;
    private String urlProfImg;
    private TipoUtente tipo;
    private SessoUtente sesso;
    private Data compleanno;
*/

create table users(
    idUser serial primary key,
    nome varchar(100),
    cognome varchar(100),
    username varchar(100),
    email varchar(150),
    password varchar(50),
    urlProfImg varchar(300),
    tipo int,
    sesso int,
    giorno int,
    mese int,
    anno int
);

/*
    private int id;
    private String titolo;
    private String contenuto;
    private String img;
    private String categoria;
    private User autore;
*/

create table news(
    idNews serial primary key,
    titolo varchar(100),
    contenuto varchar(10000),
    img varchar(150),
    didascalia varchar(150),
    catPolitica int,
    catCronaca int,
    catEsteri int,
    catEconomia int,
    catSport int,
    catCultura int,
    giorno int,
    mese int,
    anno int,
    autore bigint unsigned,
    foreign key (autore) references users(idUser) on update cascade on delete no action
);

/*
    private int id;
    private int idNews;
    private String commento;
    private User utente;
*/

create table comments(
    idComment serial primary key,
    commento varchar(10000),
    autore bigint unsigned,
    idNotizia bigint unsigned,
    foreign key (autore) references users(idUser) on update cascade on delete no action,
    foreign key (idNotizia) references news(idNews) on update cascade on delete no action
);


insert into users(nome, cognome, username, email, password, urlProfImg, tipo, sesso, giorno, mese, anno) 
    values ("Nicola", "Porceddu", "nporceddu", "nporceddu@gmail.com", "pswdnicola", "M1/avatarM.png", 1, 1, 7, 6, 1996);
insert into users(nome, cognome, username, email, password, urlProfImg, tipo, sesso, giorno, mese, anno)
    values ("Federico", "Piredda", "fpiredda", "fpiredda@gmail.com", "pswdfederico", "M1/avatarM.png", 1, 1, 28, 1, 1995);
insert into users(nome, cognome, username, email, password, urlProfImg, tipo, sesso, giorno, mese, anno)
    values ("Francesca", "Bacci", "fbacci", "fbacci@gmail.com", "pswdfrancesca", "M1/avatarF.png", 1, 2, 28, 7, 1993);
insert into users(nome, cognome, username, email, password, urlProfImg, tipo, sesso, giorno, mese, anno)
    values ("Giorgia", "Masala", "gmasala", "gmasala@gmail.com", "pswdgiorgia", "M1/avatarF.png", 2, 2, 25, 5, 1996);
insert into users(nome, cognome, username, email, password, urlProfImg, tipo, sesso, giorno, mese, anno) 
    values ("Lorenzo", "Carta", "lcarta", "lcarta@gmail.com", "pswdlorenzo", "M1/avatarM.png", 2, 1, 5, 2, 1991);
insert into users(nome, cognome, username, email, password, urlProfImg, tipo, sesso, giorno, mese, anno) 
    values ("Mauro", "Stochino", "mstochino", "mstochino@gmail.com", "pswdmauro", "M1/avatarM.png", 2, 1, 6, 1, 1993);
insert into users(nome, cognome, username, email, password, urlProfImg, tipo, sesso, giorno, mese, anno) 
    values ("Serena", "Sanna", "ssanna", "ssanna@gmail.com", "pswdserena", "M1/avatarF.png", 1, 2, 12, 8, 1989);

insert into news(titolo, contenuto, img, didascalia, catPolitica, catCronaca, catEsteri, catEconomia, catSport, catCultura, giorno, mese, anno, autore)
    values ("Non capiscono la differenza tra spingere e tirare, un diplomato su due non entra all’Università",

    "Dellavi (TA) – Una giornata davvero travagliata per i circa duecento studenti freschi dell’esame di maturità 
    che, stamattina, si sono trovati ad affrontare il test di ingresso per accedere all’Università di Dellavi. 
    Le prime difficoltà si erano già manifestate qualche giorno prima, quando le nuove promesse della società 
    avevano avuto a che fare -per la prima volta in totale autonomia- con le macchinose trappole della burocrazia italiana. 
    \"Cioè, che vuol dire che per iscrivermi non è sufficiente un account Google?\" – racconta un incredulo Bengieféde – 
    \"E veramente l’Università ha un sito tutto suo al posto di una pagina Facebook?\".
    <br/><br/>
    Il nutrito gruppo di candidati si è radunato stamattina davanti all’ingresso dell’Aula Magna dell’Università di Dellavi 
    e, subito dopo aver catturato il pokémon Blissey (molto raro), si sono trovati di fronte ad una porta a vetri. Chiusa. 
    <br/><br/>
    I battenti erano sormontati da un grosso cartello che riportava la scritta \"Test di ingresso. Apertura alle 9:00\", mentre 
    nella parte centrale c’era un secondo cartello, grigiastro, che recava una sola parola: \"Spingere\". 
    <br/><br/>
    Nient’altro. 
    <br/><br/>
    I giovani candidati, abituati ad affrontare gli strani quesiti loro somministrati durante la famigerata \"seconda prova\", 
    non si sono lasciati cogliere di sorpresa. Tutto sommato, per essere un test di accesso all’Università, sembrava alla loro 
    portata. I più fiduciosi ritenevano che per entrare fosse sufficiente attendere che il grande portone si aprisse da solo 
    \"alle 9:00\", come scritto nel cartello. I più scafati, d’altra parte, evidenziavano che se nella porta era presente un indizio 
    (\"Spingere\") qualcosa avrebbe anche dovuto significare. 
    <br/><br/>
    Alle 9:01 la porta a vetri era ancora completamente immobile davanti agli sbigottiti candidati. A quel punto, era necessario 
    risolvere l’enigma che si celava dietro la parola \"Spingere\". 
    <br/><br/>
    \"Ricordo che ha a che fare con l’esercizio di una forza – improvvisa Eusebio, brillante diplomato allo 
    scientifico-tecnologico-industrial-social-media – ma non ricordo se si tratta di una forza da esercitare in direzione della 
    porta o in direzione opposta. O meglio, meglio: la direzione è sempre quella… perpendicolare alla porta… ma il verso, da che 
    verso va?\". Gianfilippa, aspirante ingegnera gestionale prova il tutto per tutto: \"La normativa sulla sicurezza prevede che 
    le porte di ingresso si debbano aprire verso l’uscita, quindi spingere significa che deve essere esercitata una forza verso 
    lo spingitore medesimo\". Il primo tentativo del futuro scienziato motorio Uomorummo, però, non va a buon fine. La porta resta 
    immobile nella sua posizione. 
    <br/><br/>
    \"Forse non è stata applicata abbastanza forza – tenta ancora Eusebio – qualcuno ha calcolato la massa del battente?\" 
    Uomorummo, noto alle cronache per non accettare questo tipo di provocazioni, si lancia in un secondo tentativo che, però, conduce 
    solo allo sradicamento del maniglione antipanico, mentre il resto della porta rimane ancora lì, del tutto inamovibile, nonostante 
    la forza inarrestabile di Uomorummo. I più arguti si chiedono se la porta possa essere sfondata da una bici con le ruote quadrate, 
    ma nessuno li prende sul serio. 
    <br/><br/>
    Nel frattempo, un candidato sopraggiunto in ritardo, si insinua nella calca e, sorpassando tutti i colleghi, riesce inspiegabilmente 
    ad entrare. Il come, però, resta un mistero per gli astanti, nel frattempo impegnati – i più – a cercare un tutorial su youtube e 
    – i meno – a catturare un secondo Blissey. 
    <br/><br/>
    Alle ore 10:30, solo circa metà dei ragazzi era riuscita a varcare la soglia dell’Aula. I presenti, pur riportando voti mediocri 
    nel test di ingresso, sono stati tutti ammessi: \"Avevamo 120 posti per 200 candidati, ma solo 97 si sono presentati – 
    racconta il rettore dell’Università di Dellavi – quattro sono entrati dalla porta principale, novanta hanno aggirato il problema 
    entrando da dietro, infine, tre pensatori laterali si sono calati dal condotto dell’aria condizionata\". 
    <br/><br/>",

    "M1/Spingere_Uni.png", "Ragazzo che tira una porta con su scritto spingere", 0, 0, 0, 0, 0, 1, 3, 5, 2018, 1);

insert into news(titolo, contenuto, img, didascalia, catPolitica, catCronaca, catEsteri, catEconomia, catSport, catCultura, giorno, mese, anno, autore)
    values ("Graziano Cesari scopre un fermo immagine che mette in discussione il Big Bang",

    "MILANO – \"Il piede sinistro di Benatia tocca nettamente il pallone, in questa situazione non c’è fallo\". Non si sono ancora 
    spenti gli echi della rivelazione di Graziano Cesari sul rigore che ha deciso la partita Real Madrid-Juventus che arriva adesso 
    un altro clamoroso annuncio del famoso ex arbitro più abbronzato d’Italia. \"Non c’è stato il Big Bang. Da questo fermo immagine 
    si vede chiaramente che il piede destro del bosone di Higgs scivola sul brodo primordiale ma non tocca la materia, tocca il pallone 
    ma non c’è esplosione: in questa situazione non c’è Big Bang, i cosmologi non dovevano fischiare\" ha dichiarato Cesari. 
    <br/><br/>
    Non contento, Cesari ne ha approfittato per annunciare poi altre imprese: guardando al ralenty l’omonimo film avrebbe capito 
    \"Chi ha incastrato Roger Rabbit\"; ascoltando a 33 giri un LP a 45 giri di \"Non è Francesca\" di Lucio Battisti avrebbe scoperto 
    che effettivamente non era Francesca ma era una lavatrice a gettoni; analizzando al rallentatore il filmato di Abraham Zapruder l’ex 
    arbitro avrebbe inoltre capito che il presidente John Kennedy è stato ucciso. Infine, grazie a una ripresa amatoriale recuperata su 
    Internet, Cesari sarebbe perfino riuscito a identificare chi si stava scopando sua moglie mentre lui partecipava a Tiki Taka lo scorso 
    19 febbraio (era il conduttore Pierluigi Pardo, cosa risultata molto strana per tutti).
    <br/><br/>
    Il fermo immagine a cui Cesari fa riferimento per la negazione del Big Bang, è stato tratto da una puntata di Voyager in cui si parlava 
    del mistero della prima sonda aliena guidata da gnomi con l’ascia non vaccinati, che avrebbe avuto lo scopo di portare le scie chimiche 
    sulla Terra in modo da renderla ancora più piatta di quello che è e di mettere tutto il potere nelle mani degli abitanti di Atlantide 
    sopravvissuti al genocidio organizzato da Adolf Hitler dopo la sua fuga segreta in Argentina. Precisamente, un fermo immagine in cui si 
    vede Roberto Giacobbo che si scaccola.
    <br/><br/>
    La scoperta ha avuto naturalmente grande risonanza in tutta la comunità scientifica che ha immediatamente reagito spaccandosi in due. 
    C’è chi continua ad aver fede nelle equazioni della Relatività Generale, nel Modello Standard, nell’ipotesi di omogeneità e 
    nell’isotropia dell’Universo, e chi invece crede nel potere della mescalina e dichiara: \"Cesari ha ragione. E chi nega questa verità ha 
    un bidone di spazzatura al posto del cuore, farebbe meglio a restarsene a casa sua a mangiare le patatine. Se non hai la sensibilità di 
    capire che il Big Bang non c’è stato vuol dire che non sai un cazzo\".
    <br/><br/>
    E anche il mondo dello sport ha voluto prendere posizione. \"Si tratta di una delle più importanti notizie degli ultimi decenni\", ha 
    dichiarato infatti la giornalista sportiva di Sky Diletta Leotta, che ha poi aggiunto qualche altra cosa ma nessuno di noi ci ha fatto 
    caso perché le era scesa una spallina e tutto il sangue degli esseri viventi presenti è affluito completamente nei corpi cavernosi 
    creando uno stato confusionale generale.
    <br/><br/>
    Secondo indiscrezioni, comunque, Graziano Cesari avrebbe praticamente già il prossimo Premio Nobel in Fisica in tasca, che gli sarebbe 
    assegnato dall’Accademia Svedese delle Scienze con la motivazione \"per le sue fondamentali scoperte astronomiche compiute guardando 
    attentamente nella sua moviola, che ha reso strumenti come il telescopio spaziale Hubble e il cervello completamente inutili\".
    <br/><br/>",

    "M1/cesari.png", "Graziano Cesari discute sul Big Bang", 0, 0, 0, 0, 1, 1, 4, 5, 2018, 2);

insert into news(titolo, contenuto, img, didascalia, catPolitica, catCronaca, catEsteri, catEconomia, catSport, catCultura, giorno, mese, anno, autore)
    values ("Il PD si divide su come si deve dividere",

    "Scis (Si) – Nella situazione politica sempre più incerta in cui si sta ritrovando l’Italia, dal PD arriva finalmente una proposta 
    autenticamente unitaria, su cui tutti si sono trovati d’accordo: il partito deve dividersi.
    <br/><br/>
    L’annuncio del segretario reggente Maurizio Martina viene dal borgo medievale di Scis, dove il PD si è riunito in un castello di 
    proprietà del Monte dei Paschi di Siena per decidere del suo futuro. Qui 95 alti dirigenti, in rappresentanza di 115 correnti, hanno 
    certificato l’ineluttabilità della divisione. I problemi però sono nati quando si è trattato di stabilire le modalità della scissione, 
    su cui si sono avute 183 opinioni diverse.
    <br/><br/>
    Matteo Renzi non ha partecipato alla riunione per un piccolo impedimento: alcuni suoi concittadini lo hanno infatti denunciato per 
    stalking perché l’ex segretario li perseguita seguendoli in bicicletta e chiedendo loro un parere su qualunque cosa debba fare. Dopo 
    l’udienza comunque non intende presentarsi in assemblea, ma rilasciare un’intervista in TV, su consiglio di un ubriaco importunato fuori 
    dal bar principale di Rignano sull’Arno – l’unico che ormai accetta ancora di rispondergli – mentre le varie anime del partito pensano al 
    modo peggiore di dividersi.
    <br/><br/>
    Maria Elena Boschi, sempre più altoatesina, sta meditando di passare direttamente alla Südtiroler Volkspartei e ha proposto che durante la 
    riunione gli interventi si debbano rigorosamente svolgere ballando al ritmo di uno Schuhplattertanz .
    <br/><br/>
    Secondo i civatiani la cosa migliore è formare un movimento autonomo: quando sono stati informati che il loro leader ha già abbandonato il 
    PD e fondato Possibile per poi dimettersi dalla segreteria, sono fuggiti dall’assemblea in stato di shock, non prima di essersi riempiti le 
    tasche di tartine al salmone.
    <br/><br/>
    Dalla Puglia tre aspiranti esponenti emilianiani lamentano la mancata formazione di una loro fazione perché nessuno è ancora riuscito a 
    pronunciare correttamente il nome della corrente.
    <br/><br/>
    I franceschiniani insistono perché il PD abbia un ruolo in un futuro Governo, sia esso della Lega, del M5s o di Mordor. Per evidenziare 
    l’erudizione del Ministro dei Beni Culturali hanno quindi fatto proprio il detto attribuito a Francesco Guicciardini: \"Franza o Spagna purché se magna\".
    <br/><br/>
    A confondere le acque è arrivata infine una delegata delle Marche, Martina Maurizio, che ha presentato la mozione \"Site fatto caso ghe 
    avemo divisi?\", del tutto identica a quella del segretario reggente, ma con i verbi ausiliari essere e avere completamente invertiti.
    <br/><br/>
    Matteo Orfini chiude degnamente la prima giornata di dibattito dichiarando che per lui qualunque modalità di divisione va bene, purché 
    possa vantarsi di essere l’ultimo Presidente del PD, titolo che vale quanto quello di primo violino dell’orchestra del Titanic. La scissione 
    può quindi andare avanti all’insegna del motto dell’assemblea: \"Marciare divisi per colpire divisi e farsi massacrare da tutti\".
    <br/><br/>",

    "M1/PD.png", "La divisione del PD in conferenza stampa", 1, 0, 0, 0, 0, 0, 5, 5, 2018, 3);

insert into comments(commento, autore, idNotizia)
    values("Alcune volte si è proprio convinti che una porta vada aperta in un senso, invece non è così.", 4, 1);
insert into comments(commento, autore, idNotizia)
    values("Non sono bravi a dividersi ma sono bravi a rubare!", 5, 3);
insert into comments(commento, autore, idNotizia)
    values("Nel dubbio è sempre meglio aspettare che qualcuno la apra prima di te.", 6, 1);
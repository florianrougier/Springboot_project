package fr.epf.demoseptembre;

import fr.epf.demoseptembre.models.Page;
import fr.epf.demoseptembre.models.Story;
import fr.epf.demoseptembre.models.User;
import fr.epf.demoseptembre.persistence.PageDao;
import fr.epf.demoseptembre.persistence.StoryDao;
import fr.epf.demoseptembre.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
public class MainApplication {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StoryDao storyDao;
    @Autowired
    private PageDao pageDao;


    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class);
    }

    /**
     * We are calling this methode when Spring initialization is done.
     * Here, we are adding 4 new users and 1 story writen by admin
     */
    @PostConstruct
    public void init() {

        //userDao.deleteAll();
        userDao.save(new User(null, "admin", "admin"));
        userDao.save(new User(null, "florian", "florian"));
        userDao.save(new User(null, "thibault", "thibault"));
        userDao.save(new User(null, "alexandre", "alexandre"));


        // We create a new story and save it in the database
        storyDao.deleteAll();
        storyDao.save(new Story(null,
                "Les Quatres Cavaliers",
                "Une petite histoire pour vous présenter le fonctionnement du site. Affrontez les 4 Cavaliers de l’Apoplexie (Ah ! vus ne vous attendiez pas à ce nom n’est-ce pas ?) et survivez à leurs pièges pour… et bien, juste arriver à survivre !",
                userDao.findByLogin("admin").get(0),
                "img/knight.jpg"));

        // We create pages  associated with the story and save it in the database
        pageDao.deleteAll();
        pageDao.save(new Page(null,
                "L’Empereur (Trajan)",
                storyDao.findById(5).get(),
                "1",
                "« Bienvenu dans le défi des Quatres Cavaliers de l’Apoplexie ! Ces derniers ont été atteint par le même mal que le tiens ! Réussis-le, et tu pourras survivre ! Echoue, et tu rejoindras le royaume des morts ! »" +
                        "C’est par cette phrase que vous êtes accueilli. La dernière chose dont vous vous souveniez, c’est que vous étiez en train de discuter avec des amis, quand d’un coup, un malaise vous a prit et vous avez perdu connaissance… Serait-ce ça, l’Apoplexie ? Dur d’y répondre. Mais sans plus attendre, vous entendez le bruit du galop au loin, dans cette grande étendue d’un blanc immaculé. \n" +
                        "Soudain, un cavalier sorti de la brume. Ce dernier monte sur un étalon blanc magnifique, qui semble sortir d’un autre temps. Son cavalier n’en est pas moins impressionnant. Son physique n’est pourtant pas remarquable : un homme ayant atteint un âge déjà assez avancé, les cheveux gris et les traits tirés par la fatigue et le travail. Toutefois, il irradiait de cet homme une aura brillante, qui écrase n’importe qui sur lequel il pose son regard. Sa tenue était celle d’un guerrier : une cuirasse métallique lui couvrait le torse, derrière laquelle on pouvait voir une tunique brodée. Cet accoutrement était fini par des sandales fines. Il portait à sa main un pavois en bois et une lance étincelante.\n" +
                        "Il pointa alors sa lance vers vous, et dit d’une voie profonde :\n" +
                        "« Je vois que vous êtes tombés dans le Défi de l’Apoplexie. Mon nom est Augustus Trajan ! Vous connaissez les règles ! vous devez me vaincre, ainsi que les trois autres cavaliers, pour survivre ! Mon défi est simple. Voyez, derrière moi ; vous trouverez tout un arsenal d’arme. Prenez celle qui vous semble la plus adéquat ; vous m’affronterez alors en duel. Je vous attendrai ici. Vous avez tout le temps pour choisir votre arme ; mais sachez que dès que vous en aurez pris une, le duel commencera. »\n" +
                        "En découvrant l’arsenal, vous repérez tout un tas d’armes blanches, datant de l’antiquité : des arcs, des épées, lances, haches… de toutes les tailles et d’usure différente. Même si vous n’en connaissez pas tant que ça, vous repérez assez rapidement deux armes qui semblent plus appropriés pour le combat qui vous attend : une épée courte de bonne facture accompagnée d’un bouclier en bois rond cerné d’un arceau en métal pour bien le maintenir, et une longue lance à la pointe plate légère.\n"));

        pageDao.save(new Page(null,
                "L’épée et le bouclier",
                storyDao.findById(5).get(),
                "1.1",
                "A peine après avoir récupéré l’épée et le bouclier, vous entendez une lance fendre l’air. Instinctivement, vous positionnez votre bouclier au-devant du bruit qui approche. Malheureusement, le coup est destructeur ! Votre bouclier vole en morceau, et la lance se plante dans votre bras, vous infligeant une forte douleur et vous mettant à terre. « Plus aucune chance » Vous dites-vous, avant de voir Trajan sortir de la brume, un petit sourire au visage. La dernière chose que vous entendez avant de tomber inconscient est la voie de Trajan, au loin « Vous avez choisi… Bien mal »."));

        pageDao.save(new Page(null,
                "Lance Longue",
                storyDao.findById(5).get(),
                "1.2",
                "Juste après avoir pris la lance, et avoir mesuré son poids, un hennissement se fit attendre de derrière vous. A peine après vous entendez une lance fendre l’air, et vous ne pouvez que rouler sur le côté pour éviter la lance qui s’en va exploser le bouclier en un seul coup destructeur. « Aucune chance que je pare cette attaque avec le bouclier, il est fort ! Maintenant, il me suffit d’avoir assez d’allonge et de porter un coup pour le désarçonner et prendre l’avantage. »\n" +
                        "Après ce coup, Trajan apparut de l’ombre et récupéra son arme. « Ah ! vous êtes encore en vie. Intéressant. »\n" +
                        "Avant d’attaquer à nouveau, et à nouveau la seule option état d’esquiver sur le côté. Cette fois-ci, toutefois, vous avez tout le temps de voir son processus d’attaque. Certes, le coup est fort et rapide à lancer, mais le temps entre chaque coup est élevé. « C’est ma chance. » Vous dites-vous.\n" +
                        "Un nouveau coup reparti, tel un canon. Vous esquivez à nouveau, mais cette fois-ci seulement en vous baissant, et envoyez votre lance aussi fort et loin que vous pouvez, en direction de Trajan. Vous sentez quelque chose de mou au bout de votre lance, et Trajan pousser un cri de douleur avant de lâcher sa lance et de le voir tomber de son cheval.\n" +
                        "« Félicitation. Vous m’avez vaincu. Bonne chance pour le prochain combat… »\n" +
                        "Toutes les armes disparurent alors, ainsi que Trajan et le cheval. Le deuxième combat pouvait commencer.\n"));

        pageDao.save(new Page(null,
                "Le Magicien (Bach)",
                storyDao.findById(5).get(),
                "1.2.1",
                "Peu après avoir défait Trajan, vous entendez une douce mélodie envoutante envahir votre environnement. La brume se retira légèrement pour laisser apparaitre deux magnifiques pianos à queue. L’un deux est vide, mais la musique que vous entendez depuis peu maintenant provient du deuxième piano, qu’un homme d’âge mur est en train de jouer. Il semble expert dans ce domaine et possède une grande maitrise de son instrument. La musique est enivrante et semble tourner et tourner encore et encore, sans jamais s’arrêter. Vous écoutez ce morceau avec beaucoup d’attention. \n" +
                        "Mais d’un coup, la musique s’arrête. Au milieu d’une phrase. Sans être fini, et vous sors de votre torpeur. L’homme se tourne alors vers vous, et vous dit :\n" +
                        "« Eh bien ! Je suis Jean-Sébastien Bach, et le deuxième cavalier que vous aurez à affronter. Mon défi est simple ; ce morceau que vous avez entendu est en réalité un duo. Vous serez la deuxième voix. Si vous arrivez à suivre et que la performance est suffisante, vous pourrez continuer. La partition est déjà sur le piano ; je n’attends de vous uniquement que vous me fournissiez une contrepartie plaisante à l’oreille. Vous gagnerez si vous y arrivez. Allez-y, asseyez-vous, et commençons. »\n" +
                        "Heureusement pour vous, vous connaissez un petit peu de piano. La partition ne semble pas très dure au premier abord. Mais arriver à bien la jouer tout en la découvrant ? Cela risque d’être compliqué. A nouveau, deux choix s’offrent à vous. Soit essayer d’être le plus fidèle à la partition, au risque de faire plus d’erreur, ou d’arriver à improviser quelques parties, quand la lecture de la partition devient difficile.\n"));

        pageDao.save(new Page(null,
                "L’improvisation",
                storyDao.findById(5).get(),
                "1.2.1.1",
                "Le silence règne. Une tension est palpable, au moment de démarrer le morceau. Et d’un coup, l’aura de Jean-Sébastien se fit ressentir. « Ça commence. » Et le morceau démarra. Vous arrivez à suivre au début, mais le rythme s’accélère un petit peu. Au bout d’un moment, vous n’arrivez plus à suivre et décidez de passer au plan B : l’improvisation. Au bout de quelques mesures, après avoir commencé à improviser, le morceau commence à changer. Ça ne tourne plus. Une ligne droite, une direction. Fait étonnant, et pour cause : Bach ne suit plus la partition non plus. Il improvise aussi, ne regarde plus son piano. Il vous regarde, un grand sourire aux yeux. Le morceau se déroule, gardant l’origine du morceau, mais ne s’écoulant, tel un torrent, vers une fin inéluctable, en suivant le rythme et l’allure du chemin. \n" +
                        "A bout de force, après 10 minutes d’improvisation, vous vous arrêtés, sans pouvoir trouvé. « C’est fini, vous dites-vous. J’ai perdu ». Bach fini seul, en forte, et le torrent devient une cascade. Une gigantesque finale, qui se finit aussi abruptement que le morceau a commencé.\n" +
                        "Quelques secondes de silence après. Vous vous regardez. Bach vous souris et vous dis « Félicitation. Cela faisait bien longtemps que je n’avais pas joué avec autant d’entrain. Vous me plaisez. Allez-y, et remportez ce défi ! »\n"));

        pageDao.save(new Page(null,
                "Fidélité",
                storyDao.findById(5).get(),
                "1.2.1.2",
                "Le silence règne. Une tension est palpable, au moment de démarrer le morceau. Et d’un coup, l’aura de Jean-Sébastien se fit ressentir. « Ça commence. » Et le morceau démarra. Vous arrivez à suivre au début, mais le rythme s’accélère un petit peu. Au fur et à mesure, le suivi de la partition devient erratique, des erreurs arrivent, mais vous continuez. « On ne peut pas vraiment faire autre chose que le maître Bach a écrit. Ce serait inapproprié. » Au bout de 5 minutes de jeu, vous arrivez au bout de la partition, au bas de la page, mais le morceau ne semble pas s’arrêter. Vous comprenez alors avec horreur que ce morceau n’est en réalité pas fini. C’est le dernier morceau que Bach n’est jamais écrit, ou tenté d’écrire, avant de mourir d’apoplexie. D’où l’absence de titre. Vous arrivez à la dernière phrase, au dernier point d’orgue. Le morceau s’arrête, trop tôt. « Dommage », vous dit Bach. Vous aviez le potentiel d’y arriver. Si seulement vous aviez osé…"));

        pageDao.save(new Page(null,
                "L’Amoureux (Klimt)",
                storyDao.findById(5).get(),
                "1.2.1.1.1",
                "Tout se plongea alors dans le noir, pendant quelques secondes, avant de révéler une galerie remplie de tableaux, de statues et de dessins à perte de vue. Ceux sont des créations majestueuses, pleines d’or, d’ornements, rendant la galerie étincelante. Derrière vous, deux grands escaliers de part et d’autre amène vers un somptueux balcon, ou un homme, étonnamment plus sobre que toute cette somptueuse galerie, se tient.  Crane dégarni, des restes de cheveux autour des oreilles et une petite barbe. Une simple robe grive lui couvre le corps. Un pinceau et un plateau à peinture dans les mains, il travaille actuellement sur une grande œuvre. Tandis que vous vous retournez, il s’arrête et pose son pinceau et plateau, se retourne, vous souris et dit :\n" +
                        "« Bienvenu chez moi ! Je suis Gustav Klimt, peintre… à mes heures perdues. Ahahah. Cela faisait bien longtemps que je n’avais plus rencontré personne pour ce défi. Je suis le troisième Cavalier. Vous devez connaitre « Le Baiser » ; il parait que c’est l’œuvre que l’on connait le mieux de moi.  Enfin ; cela m’a donné l’idée de ce défi. Derrière moi se trouve deux portes et les deux protagonistes de cette œuvre. L’un vous tuera et l’autre vous laissera passer. \n" +
                        "\n" +
                        "Ah oui. A gauche vous aurez l’Homme, et à droite la Femme. Bonne chance.»\n" +
                        "Et, après cela, il reprit sa peinture. En montant, vous réfléchissez. Le choix est simple, soit à gauche, soit à droite. Mais finalement ils ne peuvent pas me tuer sans raison. Tout dépend donc de la relation qu’ils peuvent avoir avec moi. C’est une énigme après tout. Soit la Femme me tue car elle prend peur devant l’arrivée d’un homme dans ses quartiers, alors que l’Homme m’aurait accepté comme l’ami que je suis, leur amour étant sincère. Dans l’autre cas, la Femme n’attend qu’un amant quelle qu’il soit alors que l’Homme tentera de me tuer, me voyant comme un rival devant cette Femme dont il est conscient du cœur changeant, leur amour étant hypocrite…\n"));


        pageDao.save(new Page(null,
                "La Femme",
                storyDao.findById(5).get(),
                "1.2.1.1.1.1",
                "En ouvrant la porte, vous entrez dans ce qui semble être une chambre. La Femme, vous entendant entrer, tourna la tête avec un petit cri de surprise. Apparemment, elle n’attendait personne. Vous voyant ainsi entrer, elle sourit, et s’approcha de vous « bonjour jeune personne. Je n’attendais personne. Vous sembler bien mal éduqué, pour entrer chez une femme ainsi sans frapper… »\n" +
                        "Elle s’approcha, langoureusement vers vous. Son corps, magnifique, ne pouvait attirer que le regard et l’envie… Vous vous approché un sourire au lèvre…\n" +
                        "Avant de sentir une lame froide, dans votre ventre. « Je sais pourquoi vous êtes ici… et mon amour pour mon Homme est invincible… Adieu »\n"));

        pageDao.save(new Page(null,
                "L’Homme",
                storyDao.findById(5).get(),
                "1.2.1.1.1.2",
                "En ouvrant la porte, vous entrez dans ce qui semble être un bar. L’Homme est là, assis sur un siège près du bar, en train de boire un verre de whisky. « Ah ! Tu es enfin arrivé. Assieds-toi un peu ici si tu as le temps, qu’on discute. Sinon, on m’a dit que tu étais attendu dehors, derrière le bar. Un gars en costume noir m’a demandé de te dire « Félicitations », je crois… Quelque chose comme ça.\n" +
                        "Oh, dis-donc, je n’avais pas vu l’heure ! Il faut que j’y aille, ma Femme m’attend » dit-il après avoir regardé sa montre, et sortie de là où vous êtes entrés.\n" +
                        "Vous décidez alors de suivre son conseil, et d’aller à l’arrière du bar, là où le Quatrième Cavalier vous attend…\n"));

        pageDao.save(new Page(null,
                "Le Démon (Al Capone)",
                storyDao.findById(5).get(),
                "1.2.1.1.1.2.1",
                "En sortant du bar, vous voyez une voiture noire avec vitre teintés, garés devant le bar. Une fois sorti, la portière s’ouvre. Vous entrez donc à l’intérieur.\n" +
                        "L’espace de la voiture est assez large pour permettre d’avoir deux banquettes se faisant face. Une fois entré, la portière se ferme, et la voiture démarre. En face de vous, se trouve un homme d’age moyen, doté d’un embonpoint important, assez joufflu et le crâne dégarni. Il vous sourit d’un sourire à glacer le sang avant de vous parler. \n" +
                        "« Eh bien ! Vous êtes la première personne à arriver à m’atteindre. Je suis le Dernier Cavalier, Al Capone. J’espère que ce petit jeu vous plait autant que moi. C’était amusant, ahah ! de vous voir avoir tant de problème à affronter toutes ces personnalités. Mais évidemment, si vous voulez remporter ce défi, vous devrez me vaincre…\n" +
                        "C’est simple. Nous allons faire une roulette russe. Une balle dans ce pistolet, les 5 autres emplacements vides. On tire une fois chacun son tour. Tu choisis qui commence. »\n"));

        pageDao.save(new Page(null,
                "Jouer en premier",
                storyDao.findById(5).get(),
                "1.2.1.1.1.2.1.1",
                "Vous décidez d’être courageux et de prendre le premier tir. Ce n’est pas le choix le plus logique, mais de toute façon, il suffit d’être chanceux…\n" +
                        "Premier tir… Clic. Vous êtes en vie.\n" +
                        "Deuxième tir… Clic. Al Capone survit lui aussi et vous donne le pistolet.\n" +
                        "Troisième tir… vous êtes anxieux. Une chance sur 3… Clic.\n" +
                        "Quatrième tir… Al Capone tire sans sourciller… PAN. Il meurt, et vous survivez.\n" +
                        "La voiture s’arrête alors, et le chauffeur se tourne vers vous.\n" +
                        "« Félicitation, vous avez réussi le défi, et déjoué La Mort. C’est-à-dire, moi. Vous reviendrez dans le monde des vivants prochainement. Profitez-en bien. Ah ! Au moins cela a rendu les choses amusantes… »\n" +
                        "Le monde disparait alors et devient d’un noir d’encre, et vous perdez connaissance à nouveau, avant de vous réveiller dans un lit d’hôpital, au près de vos parents, qui s’extasie de joie devant votre survie, et vous de même, bien que vous ne vous souvenez de rien…\n"));

        pageDao.save(new Page(null,
                "Jouer en deuxième",
                storyDao.findById(5).get(),
                "1.2.1.1.1.2.1.2",
                "Le choix le plus logique est de jjouer en deuxième. Vous tendez donc le pistolet sans un mot à Capone, qui sourit, et sans hésitation pointe le pistolet sur sa tempe.\n" +
                        "Premier tir… Clic. Al Capone survit lui aussi et vous donne le pistolet.\n" +
                        "Deuxième tir…PAN. Vous n’avez le temps de rien voir venir, que vous êtes mort.\n"));

    }
}
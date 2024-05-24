import Datastore from 'nedb';

const postsDB = new Datastore();

// Dados mockados
const objMock = [
    {
        "titulo": "Building Reddit Recap with Jetpack Compose on Android",
        "data": "05/04/2023 21:00",
        "descricao": "This is a guest post from Reddit Engineer, Aaron Oertel, on how they’ve leveraged Compose in their Android App. When we first brought Reddit Recap to our users in late 2021, it was a huge success and we knew that it would come back in 2022. And while there was only one year in between, the way we build mobile apps at Reddit fundamentally changed which made us rebuild the Recap experience from the ground up with a more vibrant user experience, rich animations and advanced sharing capabilities. One of the biggest changes was the introduction of Jetpack Compose and our composition-based presentation architecture. To fully leverage our reactive UI architecture we decided to rewrite all of the UI from the ground up in Compose. We deemed it to be worth it since Compose would allow us to express our UI with simple, reusable components. In this post, we will cover how we leveraged Jetpack Compose to build a shiny new Reddit Recap experience for our users by creating reusable UI components, leveraging declarative animations and making the whole experience buttery smooth. Hopefully you will be as bananas over Compose as we are after hearing about our experience."
    },
    {
        "titulo": "Profile guided optimization for native Android applications",
        "data": "14/07/2020 20:30",
        "descricao": "Posted by Pirama Arumuga Nainar, Software Engineer. Profile-guided optimization (PGO) is a well known compiler optimization technique. In PGO, runtime profiles from a program’s executions are used by the compiler to make optimal choices about inlining and code layout. This leads to improved performance and reduced code size. Developers can now leverage Google’s toolkit to easily deploy PGO tools and improve their native Android apps. On selected Android system components, enabling PGO improved performance by 6–8%. PGO also provided code-size improvements in one component while slightly increasing the code size of the other two components."
    },
    {
        "titulo": "Navigation Compose meet Type Safety",
        "data": "01/05/2024 12:00",
        "descricao": "Bringing Safe Args to Navigation Compose /n As of Navigation 2.8.0-alpha08, the Navigation Component has a full type safe system based on Kotlin Serialization for defining your navigation graph when using our Kotlin DSL, designed to work best with integrations like Navigation Compose.Kotlin DSL? What’s that for? The Navigation Component has three main components: Host — the UI element in your layout that displays the current ‘destination’ Graph — the data structure that defines all of the possible destinations in your app Controller — the central coordinator that manages navigating between destinations and saving the back stack of destinations The Kotlin DSL is just one of the ways to build that navigation graph. Since the very first alpha of Navigation back in 2018, Navigation has always offered three ways to build the graph: Manually constructing instances of NavGraph and adding destinations like Fragment Destinations to construct the graph (this is still the underlying base for everything else, but not something you should actively be doing yourself) Inflating your graph from a navigation XML file, editing it by hand or via the Navigation Editor Using the Kotlin DSL to construct your navigation graph directly in your Kotlin code Navigation Compose was the first integration to really embrace the Kotlin DSL as the way to build your graph, purposefully moving to a more flexible system and away from static XML files. Kotlin code, but at what cost? However, the move from build time static XML files to generating your graph at runtime meant that the tools available to developers also changed significantly. The Navigation Component’s Safe Args Gradle Plugin, which generated type safe “Directions” classes you could use in your code to navigate between destinations, relied on reading the destinations and their arguments from those navigation XML files. That means without navigation XML files, there was no generated Safe Args code. And while Navigation requires the correct types and arguments at runtime (telling you loudly (crashing) if you tried to pass a String to something expecting an Int or if you forgot a required argument), compile time safety was left as an exercise to the developer."
    }
];

// Inserir os dados mockados no banco de dados
postsDB.insert(objMock, (err, newDocs) => {
    if (err) {
        console.error('Erro ao inserir dados:', err);
    } else {
        console.log('Dados inseridos com sucesso:', newDocs);
    }
});

export default {
    postsDB
};
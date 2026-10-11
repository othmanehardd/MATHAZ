package com.example.data

import com.example.model.*

object MathData {

  val chapters: List<Chapter> = listOf(
    // --- 1AC Semestre 1 ---
    Chapter(
      id = "1ac_s1_c1",
      level = SchoolLevel.AC1,
      semester = Semester.S1,
      title = "Opérations sur les nombres entiers et décimaux",
      subtitle = "Priorités opératoires, parenthèses et distributivité du calcul.",
      order = 1
    ),
    Chapter(
      id = "1ac_s1_c2",
      level = SchoolLevel.AC1,
      semester = Semester.S1,
      title = "Fractions et opérations",
      subtitle = "Égalité de fractions, somme, différence, produit et quotient.",
      order = 2
    ),
    Chapter(
      id = "1ac_s1_c3",
      level = SchoolLevel.AC1,
      semester = Semester.S1,
      title = "Nombres relatifs : Définition et comparaison",
      subtitle = "Droite graduée, repérage, valeur absolue et ordre.",
      order = 3
    ),
    Chapter(
      id = "1ac_s1_c4",
      level = SchoolLevel.AC1,
      semester = Semester.S1,
      title = "Géométrie dans le plan et Inégalité triangulaire",
      subtitle = "Points, segments, médiatrice et construction de triangles.",
      order = 4
    ),

    // --- 1AC Semestre 2 ---
    Chapter(
      id = "1ac_s2_c1",
      level = SchoolLevel.AC1,
      semester = Semester.S2,
      title = "Puissances de 10 et puissances d'un nombre relatif",
      subtitle = "Règles de calcul et notation scientifique de base.",
      order = 5
    ),
    Chapter(
      id = "1ac_s2_c2",
      level = SchoolLevel.AC1,
      semester = Semester.S2,
      title = "Calcul littéral et simplification",
      subtitle = "Développement simple et réduction d'expressions algébriques.",
      order = 6
    ),
    Chapter(
      id = "1ac_s2_c3",
      level = SchoolLevel.AC1,
      semester = Semester.S2,
      title = "Équations du premier degré à une inconnue",
      subtitle = "Résolution méthodique de ax + b = c et problèmes simples.",
      order = 7
    ),
    Chapter(
      id = "1ac_s2_c4",
      level = SchoolLevel.AC1,
      semester = Semester.S2,
      title = "Symétrie axiale et angles",
      subtitle = "Conservation des longueurs, mesures d'angles et aires.",
      order = 8
    ),

    // --- 2AC Semestre 1 ---
    Chapter(
      id = "2ac_s1_c1",
      level = SchoolLevel.AC2,
      semester = Semester.S1,
      title = "Nombres rationnels : Introduction et opérations",
      subtitle = "Propriétés fondamentales, addition, multiplication et inverse.",
      order = 1
    ),
    Chapter(
      id = "2ac_s1_c2",
      level = SchoolLevel.AC2,
      semester = Semester.S1,
      title = "Puissances et notation scientifique",
      subtitle = "Puissances à exposants entiers relatifs et applications.",
      order = 2
    ),
    Chapter(
      id = "2ac_s1_c3",
      level = SchoolLevel.AC2,
      semester = Semester.S1,
      title = "Calcul littéral, développement et factorisation",
      subtitle = "Distributivité double et premières factorisations.",
      order = 3
    ),
    Chapter(
      id = "2ac_s1_c4",
      level = SchoolLevel.AC2,
      semester = Semester.S1,
      title = "Théorème de Pythagore (Introduction)",
      subtitle = "Le triangle rectangle et la relation entre ses côtés.",
      order = 4
    ),

    // --- 2AC Semestre 2 ---
    Chapter(
      id = "2ac_s2_c1",
      level = SchoolLevel.AC2,
      semester = Semester.S2,
      title = "Théorème de Thalès dans le triangle",
      subtitle = "Droites parallèles, proportionnalité des longueurs.",
      order = 5
    ),
    Chapter(
      id = "2ac_s2_c2",
      level = SchoolLevel.AC2,
      semester = Semester.S2,
      title = "Équations et résolution de problèmes",
      subtitle = "Équations du premier degré et mise en équation de problèmes réels.",
      order = 6
    ),
    Chapter(
      id = "2ac_s2_c3",
      level = SchoolLevel.AC2,
      semester = Semester.S2,
      title = "Proportionnalité et fonctions linéaires",
      subtitle = "Coefficient de proportionnalité et représentation graphique.",
      order = 7
    ),
    Chapter(
      id = "2ac_s2_c4",
      level = SchoolLevel.AC2,
      semester = Semester.S2,
      title = "Prismes droits et Cylindres de révolution",
      subtitle = "Patrons, aires latérales et volumes des solides de l'espace.",
      order = 8
    ),

    // --- 3AC Semestre 1 ---
    Chapter(
      id = "3ac_s1_c1",
      level = SchoolLevel.AC3,
      semester = Semester.S1,
      title = "Racines carrées",
      subtitle = "Propriétés opératoires, simplification et élimination du radical au dénominateur.",
      order = 1
    ),
    Chapter(
      id = "3ac_s1_c2",
      level = SchoolLevel.AC3,
      semester = Semester.S1,
      title = "Développement, factorisation et identités remarquables",
      subtitle = "Les 3 identités remarquables, développement méthodique et factorisation experte.",
      order = 2
    ),
    Chapter(
      id = "3ac_s1_c3",
      level = SchoolLevel.AC3,
      semester = Semester.S1,
      title = "Théorème de Pythagore et sa réciproque",
      subtitle = "Calculs d'hypoténuse, démonstration de l'orthogonalité et applications au brevet.",
      order = 3
    ),
    Chapter(
      id = "3ac_s1_c4",
      level = SchoolLevel.AC3,
      semester = Semester.S1,
      title = "Théorème de Thalès et sa réciproque",
      subtitle = "Configurations papillon et triangle, calculs de segments et parallélisme.",
      order = 4
    ),
    Chapter(
      id = "3ac_s1_c5",
      level = SchoolLevel.AC3,
      semester = Semester.S1,
      title = "Trigonométrie dans le triangle rectangle",
      subtitle = "Cosinus, sinus, tangente, relations trigonométriques et angles remarquables.",
      order = 5
    ),
    Chapter(
      id = "3ac_s1_c6",
      level = SchoolLevel.AC3,
      semester = Semester.S1,
      title = "Ordre et opérations",
      subtitle = "Inégalités, encadrements, addition et multiplication d'inégalités.",
      order = 6
    ),
    Chapter(
      id = "3ac_s1_c7",
      level = SchoolLevel.AC3,
      semester = Semester.S1,
      title = "Angles inscrits et angles au centre",
      subtitle = "Mesures d'angles dans un cercle et polygones réguliers.",
      order = 7
    ),

    // --- 3AC Semestre 2 ---
    Chapter(
      id = "3ac_s2_c1",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Équations et inéquations du premier degré",
      subtitle = "Équations produits nuls, inéquations et représentation graphique des solutions.",
      order = 8
    ),
    Chapter(
      id = "3ac_s2_c2",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Vecteurs et translation",
      subtitle = "Égalité de vecteurs, relation de Chasles et construction de points sous translation.",
      order = 9
    ),
    Chapter(
      id = "3ac_s2_c3",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Repère dans le plan",
      subtitle = "Coordonnées d'un point, d'un vecteur, distance entre deux points et milieu d'un segment.",
      order = 10
    ),
    Chapter(
      id = "3ac_s2_c4",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Équation d'une droite",
      subtitle = "Pente (coefficient directeur), ordonnée à l'origine, droites parallèles et perpendiculaires.",
      order = 11
    ),
    Chapter(
      id = "3ac_s2_c5",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Systèmes de deux équations à deux inconnues",
      subtitle = "Méthode par substitution, méthode par combinaison linéaire et résolution graphique.",
      order = 12
    ),
    Chapter(
      id = "3ac_s2_c6",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Fonctions linéaires et fonctions affines",
      subtitle = "Notion de fonction, f(x) = ax, g(x) = ax + b, images, antécédents et droites.",
      order = 13
    ),
    Chapter(
      id = "3ac_s2_c7",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Statistiques",
      subtitle = "Moyenne arithmétique, médiane, mode, fréquences cumulées et diagrammes.",
      order = 14
    ),
    Chapter(
      id = "3ac_s2_c8",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Géométrie dans l'espace",
      subtitle = "Pyramides, cônes, sections planes, agrandissement et réduction de volumes.",
      order = 15
    )
  )

  val lessons: List<Lesson> = listOf(
    // 3AC S1: Théorème de Pythagore
    Lesson(
      id = "lesson_pythagore_3ac",
      chapterId = "3ac_s1_c3",
      level = SchoolLevel.AC3,
      title = "Théorème de Pythagore direct et réciproque",
      chapterTitle = "Théorème de Pythagore",
      summary = "Ce chapitre fondamental permet de calculer des longueurs dans un triangle rectangle et de démontrer qu'un triangle est rectangle.",
      tableOfContents = listOf(
        "1. Vocabulaire du triangle rectangle",
        "2. Théorème direct de Pythagore",
        "3. Réciproque du théorème de Pythagore",
        "4. Méthodes de calcul pas à pas",
        "5. Pièges fréquents à éviter",
        "6. Exercices d'application recommandés"
      ),
      definitions = listOf(
        MathDefinition(
          term = "Hypoténuse",
          explanation = "L'hypoténuse est le côté opposé à l'angle droit dans un triangle rectangle. C'est toujours le côté le plus long du triangle.",
          formula = "[BC] est l'hypoténuse si le triangle ABC est rectangle en A"
        ),
        MathDefinition(
          term = "Triangle rectangle",
          explanation = "Un triangle ayant un angle droit de 90°. La somme des deux autres angles aigus vaut 90° (angles complémentaires).",
          formula = "Angle(BAC) = 90°"
        )
      ),
      theorems = listOf(
        MathTheorem(
          title = "Théorème direct de Pythagore",
          statement = "Dans un triangle rectangle, le carré de la longueur de l'hypoténuse est égal à la somme des carrés des longueurs des deux côtés de l'angle droit.",
          formula = "BC² = AB² + AC²",
          conditions = "Hypothèse obligatoire : Le triangle ABC doit être rectangle en A."
        ),
        MathTheorem(
          title = "Réciproque du théorème de Pythagore",
          statement = "Si dans un triangle ABC, le carré du plus grand côté est égal à la somme des carrés des deux autres côtés, alors ce triangle est rectangle.",
          formula = "Si BC² = AB² + AC², alors ABC est rectangle en A.",
          conditions = "Permet de PROUVER qu'un triangle est rectangle à partir des longueurs de ses trois côtés."
        )
      ),
      workedExamples = listOf(
        MathExample(
          title = "Calculer l'hypoténuse d'un triangle",
          problem = "Soit un triangle ABC rectangle en A tel que AB = 3 cm et AC = 4 cm. Calculer la longueur de l'hypoténuse BC.",
          steps = listOf(
            "1. Citer l'hypothèse : ABC est un triangle rectangle en A.",
            "2. Énoncer le théorème : D'après le théorème de Pythagore : BC² = AB² + AC².",
            "3. Remplacer par les valeurs numériques : BC² = 3² + 4² = 9 + 16 = 25.",
            "4. Prendre la racine carrée positive : BC = √25 = 5 cm."
          ),
          finalAnswer = "BC = 5 cm"
        ),
        MathExample(
          title = "Calculer un côté de l'angle droit",
          problem = "Soit EFG un triangle rectangle en E avec FG = 13 cm et EF = 5 cm. Calculer la longueur EG.",
          steps = listOf(
            "1. D'après le théorème de Pythagore : FG² = EF² + EG².",
            "2. Isoler le côté recherché : EG² = FG² - EF².",
            "3. Calcul numérique : EG² = 13² - 5² = 169 - 25 = 144.",
            "4. Conclusion : EG = √144 = 12 cm."
          ),
          finalAnswer = "EG = 12 cm"
        )
      ),
      diagramType = DiagramType.PYTHAGORAS_TRIANGLE,
      commonMistakes = listOf(
        "Oublier de vérifier ou de mentionner que le triangle est rectangle avant d'utiliser la formule.",
        "Confondre l'addition et la soustraction : pour calculer l'hypoténuse, on additionne les carrés ; pour un côté de l'angle droit, on soustrait.",
        "Écrire BC = AB + AC au lieu de BC² = AB² + AC² (l'égalité porte sur les carrés, pas sur les longueurs !)."
      ),
      keyPoints = listOf(
        "Hypoténuse² = Côté1² + Côté2².",
        "Pour tester si un triangle est rectangle : calcule séparément le carré du plus grand côté, puis la somme des carrés des deux autres.",
        "Triplets pythagoriciens classiques à connaître par cœur : (3, 4, 5), (6, 8, 10), (5, 12, 13)."
      ),
      estimatedMinutes = 25,
      pdfDocuments = listOf(
        LessonPdfDocument(
          id = "pdf_pyth_1",
          title = "Cours complet & Démonstrations du Théorème de Pythagore",
          fileName = "Pythagore_Cours_Complet_3AC.pdf",
          fileSize = "1.8 Mo",
          pagesCount = 4,
          category = "Fiche de cours",
          description = "Propriétés directes et réciproques, méthode de calcul et théorèmes encadrés.",
          samplePreviewContent = listOf(
            "Royaume du Maroc — Ministère de l'Éducation Nationale",
            "Établissement Collégial • Niveau 3AC (Troisième Année Collège)",
            "Chapitre : Théorème de Pythagore direct et réciproque",
            "I. Configuration géométrique dans le triangle rectangle",
            "Soit ABC un triangle rectangle en A. L'hypoténuse est le segment [BC].",
            "Énoncé : Dans tout triangle rectangle, le carré de l'hypoténuse est égal à la somme des carrés des deux autres côtés : BC² = AB² + AC².",
            "II. Réciproque du théorème de Pythagore",
            "Si dans un triangle ABC on a BC² = AB² + AC², alors le triangle est rectangle en A.",
            "III. Exemples d'application & Barème brevet (Session normale Juin)",
            "Exercice type : ABC tel que AB = 3 cm, AC = 4 cm => BC = √(9 + 16) = √25 = 5 cm."
          )
        ),
        LessonPdfDocument(
          id = "pdf_pyth_2",
          title = "Fiche résumé & Formules incontournables (Synthèse Recto-Verso)",
          fileName = "Pythagore_Fiche_Synthese.pdf",
          fileSize = "620 Ko",
          pagesCount = 2,
          category = "Résumé synthétique",
          description = "Synthèse visuelle recto-verso à imprimer pour les révisions rapides.",
          samplePreviewContent = listOf(
            "Fiche Mémo Rapide — Théorème de Pythagore (3AC)",
            "Formule direct : c² = a² + b² => c = √(a² + b²)",
            "Côté de l'angle droit : a² = c² - b² => a = √(c² - b²)",
            "Triplets pythagoriciens : (3, 4, 5) ; (6, 8, 10) ; (5, 12, 13)",
            "Attention : toujours préciser que le triangle est rectangle !"
          )
        ),
        LessonPdfDocument(
          id = "pdf_pyth_3",
          title = "Série d'exercices d'application avec corrigé et barème sur 20",
          fileName = "Pythagore_Serie_Exercices.pdf",
          fileSize = "1.2 Mo",
          pagesCount = 3,
          category = "Série d'exercices",
          description = "10 exercices progressifs avec solutions détaillées pas à pas.",
          samplePreviewContent = listOf(
            "Série d'Exercices N° 1 : Théorème de Pythagore",
            "Exercice 1 (4 pts) : Calculs d'hypoténuses avec radicaux simplifiés.",
            "Exercice 2 (6 pts) : Démonstration de l'orthogonalité par la réciproque.",
            "Exercice 3 (5 pts) : Application aux solides de l'espace (Pyramide).",
            "Exercice 4 (5 pts) : Problème concret de géométrie appliquée."
          )
        )
      )
    ),

    // 3AC S1: Racines carrées
    Lesson(
      id = "lesson_racines_3ac",
      chapterId = "3ac_s1_c1",
      level = SchoolLevel.AC3,
      title = "Racines carrées : Propriétés et Simplifications",
      chapterTitle = "Racines carrées",
      summary = "Maîtriser les propriétés des radicaux, la simplification d'écritures avec √a et l'élimination du radical au dénominateur.",
      tableOfContents = listOf(
        "1. Définition de la racine carrée d'un nombre positif",
        "2. Propriétés opératoires fondamentales",
        "3. Simplification d'expressions avec radicaux",
        "4. Rendre rationnel le dénominateur (Quantité conjuguée)",
        "5. Erreurs classiques",
        "6. Bilan et synthèse"
      ),
      definitions = listOf(
        MathDefinition(
          term = "Racine carrée (√a)",
          explanation = "Pour tout nombre réel positif ou nul a, la racine carrée de a est l'unique nombre réel positif dont le carré est égal à a.",
          formula = "(√a)² = a  et  √(a²) = a  (avec a ≥ 0)"
        )
      ),
      theorems = listOf(
        MathTheorem(
          title = "Produit et quotient de racines",
          statement = "La racine du produit est le produit des racines. La racine du quotient est le quotient des racines.",
          formula = "√(a × b) = √a × √b   et   √(a / b) = √a / √b (pour a ≥ 0, b > 0)",
          conditions = "ATTENTION : √(a + b) ≠ √a + √b en général !"
        ),
        MathTheorem(
          title = "Élimination du radical (Quantité conjuguée)",
          statement = "Pour éliminer la racine carrée d'un dénominateur du type (a - √b), on multiplie numérateur et dénominateur par (a + √b).",
          formula = "1 / (a - √b) = (a + √b) / (a² - b)",
          conditions = "Utilise l'identité remarquable (x - y)(x + y) = x² - y²."
        )
      ),
      workedExamples = listOf(
        MathExample(
          title = "Simplifier √75",
          problem = "Écrire √75 sous la forme a√b où a et b sont des entiers et b est le plus petit possible.",
          steps = listOf(
            "1. Décomposer 75 avec un carré parfait : 75 = 25 × 3.",
            "2. Appliquer la propriété du produit : √75 = √(25 × 3) = √25 × √3.",
            "3. Calculer √25 = 5.",
            "4. Résultat simplifié : 5√3."
          ),
          finalAnswer = "√75 = 5√3"
        ),
        MathExample(
          title = "Rendre rationnel le dénominateur de 6 / √3",
          problem = "Supprimer la racine carrée au dénominateur du quotient 6 / √3.",
          steps = listOf(
            "1. Multiplier numérateur et dénominateur par √3 : (6 × √3) / (√3 × √3).",
            "2. Simplifier le dénominateur : (√3)² = 3.",
            "3. Calculer le quotient : (6√3) / 3 = 2√3."
          ),
          finalAnswer = "2√3"
        )
      ),
      diagramType = null,
      commonMistakes = listOf(
        "Croire que √(a + b) = √a + √b. Exemple : √(9 + 16) = √25 = 5, alors que √9 + √16 = 3 + 4 = 7 !",
        "Écrire la racine carrée d'un nombre négatif : √(-9) n'existe pas dans l'ensemble des réels.",
        "Oublier que √a est TOUJOURS positif ou nul par définition."
      ),
      keyPoints = listOf(
        "Toujours chercher les carrés parfaits : 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144.",
        "Pour a > 0 et b > 0, √(a² × b) = a√b.",
        "Pour éliminer √b au dénominateur simple : multiplie en haut et en bas par √b."
      ),
      estimatedMinutes = 20,
      pdfDocuments = listOf(
        LessonPdfDocument(
          id = "pdf_rac_1",
          title = "Propriétés opératoires des Racines Carrées (Cours PDF)",
          fileName = "Racines_Carrees_Cours_3AC.pdf",
          fileSize = "1.4 Mo",
          pagesCount = 3,
          category = "Fiche de cours",
          description = "Définition, règles du produit, quotient et simplification de radicaux.",
          samplePreviewContent = listOf(
            "Royaume du Maroc • Collège 3AC",
            "Chapitre : Racines carrées & Radicaux",
            "I. Définition : √(a) pour tout nombre réel positif ou nul",
            "II. Propriétés fondamentales : √(a × b) = √a × √b",
            "III. Quotient : √(a / b) = √a / √b (b > 0)",
            "IV. Attention aux pièges : √(a + b) ≠ √a + √b"
          )
        ),
        LessonPdfDocument(
          id = "pdf_rac_2",
          title = "Techniques de la quantité conjuguée & Élimination du radical (PDF)",
          fileName = "Quantite_Conjuguee_Fiche.pdf",
          fileSize = "750 Ko",
          pagesCount = 2,
          category = "Méthodes à retenir",
          description = "Méthodes pas à pas pour supprimer les racines du dénominateur.",
          samplePreviewContent = listOf(
            "Fiche Méthode : Rendre rationnel le dénominateur",
            "Cas 1 : Forme a / √b => Multiplier par √b en haut et en bas",
            "Cas 2 : Forme 1 / (a - √b) => Multiplier par le conjugué (a + √b)",
            "Identité utilisée : (a - √b)(a + √b) = a² - b"
          )
        )
      )
    ),

    // 3AC S1: Identités Remarquables
    Lesson(
      id = "lesson_identites_3ac",
      chapterId = "3ac_s1_c2",
      level = SchoolLevel.AC3,
      title = "Identités Remarquables : Développement et Factorisation",
      chapterTitle = "Développement et Identités remarquables",
      summary = "Les trois identités remarquables sont au cœur des épreuves du brevet et de l'examen régional normalisé marocain.",
      tableOfContents = listOf(
        "1. Les trois formules d'or",
        "2. Développer avec les identités remarquables",
        "3. Factoriser avec les identités remarquables",
        "4. Exemples types brevet",
        "5. Erreurs courantes"
      ),
      definitions = listOf(
        MathDefinition(
          term = "Développement",
          explanation = "Transformer un produit de facteurs en une somme ou différence de termes.",
          formula = "Produit ➔ Somme"
        ),
        MathDefinition(
          term = "Factorisation",
          explanation = "Transformer une somme ou différence de termes en un produit de facteurs.",
          formula = "Somme ➔ Produit"
        )
      ),
      theorems = listOf(
        MathTheorem(
          title = "Première identité remarquable (Carré d'une somme)",
          statement = "Le carré de la somme de deux réels a et b est égal au carré du premier, plus le double produit, plus le carré du second.",
          formula = "(a + b)² = a² + 2ab + b²"
        ),
        MathTheorem(
          title = "Deuxième identité remarquable (Carré d'une différence)",
          statement = "Le carré de la différence de deux réels a et b est égal au carré du premier, moins le double produit, plus le carré du second.",
          formula = "(a - b)² = a² - 2ab + b²"
        ),
        MathTheorem(
          title = "Troisième identité remarquable (Différence de deux carrés)",
          statement = "Le produit de la somme par la différence est égal à la différence des carrés.",
          formula = "(a - b)(a + b) = a² - b²"
        )
      ),
      workedExamples = listOf(
        MathExample(
          title = "Développer (2x + 3)²",
          problem = "Développer et réduire l'expression A = (2x + 3)².",
          steps = listOf(
            "1. Identifier a et b : a = 2x et b = 3.",
            "2. Appliquer la formule : A = (2x)² + 2 × (2x) × 3 + 3².",
            "3. Calculer chaque terme : (2x)² = 4x², 2 × 2x × 3 = 12x, 3² = 9.",
            "4. Conclusion : A = 4x² + 12x + 9."
          ),
          finalAnswer = "4x² + 12x + 9"
        ),
        MathExample(
          title = "Factoriser 9x² - 16",
          problem = "Factoriser l'expression B = 9x² - 16.",
          steps = listOf(
            "1. Reconnaître la forme a² - b².",
            "2. 9x² = (3x)² et 16 = 4².",
            "3. Appliquer la 3ème identité : B = (3x - 4)(3x + 4)."
          ),
          finalAnswer = "(3x - 4)(3x + 4)"
        )
      ),
      diagramType = null,
      commonMistakes = listOf(
        "Oublier le double produit 2ab ! Écrire (a + b)² = a² + b² est une erreur éliminatoire.",
        "Oublier les parenthèses lors du calcul du carré d'un monôme : (2x)² = 4x² et non pas 2x².",
        "Attention aux signes avec les parenthèses précédées d'un signe moins : -(a - b) = -a + b."
      ),
      keyPoints = listOf(
        "(a + b)² = a² + 2ab + b²",
        "(a - b)² = a² - 2ab + b²",
        "(a - b)(a + b) = a² - b² ➔ Formule reine pour factoriser les différences de carrés."
      ),
      estimatedMinutes = 20
    ),

    // 3AC S1: Théorème de Thalès
    Lesson(
      id = "lesson_thales_3ac",
      chapterId = "3ac_s1_c4",
      level = SchoolLevel.AC3,
      title = "Théorème de Thalès direct et réciproque",
      chapterTitle = "Théorème de Thalès",
      summary = "Propriété de proportionnalité géométrique permettant de calculer des longueurs et de prouver le parallélisme de deux droites.",
      tableOfContents = listOf(
        "1. Configurations de Thalès (Triangle & Papillon)",
        "2. Théorème direct de Thalès",
        "3. Réciproque de Thalès",
        "4. Application au calcul de longueurs",
        "5. Démontrer que deux droites sont parallèles"
      ),
      definitions = listOf(
        MathDefinition(
          term = "Configuration de Thalès",
          explanation = "Deux droites sécantes en un point A, coupées par deux droites parallèles (BC) et (MN).",
          formula = "(MN) // (BC)"
        )
      ),
      theorems = listOf(
        MathTheorem(
          title = "Théorème direct de Thalès",
          statement = "Si les points A, M, B d'une part et A, N, C d'autre part sont alignés dans cet ordre, et si (MN) est parallèle à (BC), alors les rapports des longueurs sont égaux.",
          formula = "AM / AB = AN / AC = MN / BC",
          conditions = "Permet de calculer une longueur manquante."
        ),
        MathTheorem(
          title = "Réciproque du théorème de Thalès",
          statement = "Si les points A, M, B et A, N, C sont alignés dans le même ordre et si AM / AB = AN / AC, alors les droites (MN) et (BC) sont parallèles.",
          formula = "AM / AB = AN / AC ➔ (MN) // (BC)",
          conditions = "Ne pas oublier de vérifier et mentionner l'ordre des points alignés !"
        )
      ),
      workedExamples = listOf(
        MathExample(
          title = "Calculer la longueur MN",
          problem = "Soit un triangle ABC avec AB = 8 cm, AC = 6 cm, BC = 10 cm. M est sur [AB] avec AM = 2 cm, et (MN) est parallèle à (BC). Calculer MN.",
          steps = listOf(
            "1. Les points A, M, B et A, N, C sont alignés, et (MN) // (BC).",
            "2. D'après le théorème de Thalès : AM / AB = MN / BC.",
            "3. Remplacement : 2 / 8 = MN / 10.",
            "4. Produit en croix : MN = (2 × 10) / 8 = 20 / 8 = 2.5 cm."
          ),
          finalAnswer = "MN = 2.5 cm"
        )
      ),
      diagramType = DiagramType.THALES_CONFIG,
      commonMistakes = listOf(
        "Inverser numérateur et dénominateur dans les rapports : toujours garder le petit triangle au numérateur et le grand au dénominateur (ou l'inverse de façon constante).",
        "Oublier de préciser que les points sont alignés dans le même ordre lors de l'application de la réciproque."
      ),
      keyPoints = listOf(
        "Rapports égaux : Petit côté / Grand côté = Petit côté / Grand côté = Côté parallèle / Côté parallèle.",
        "Produit en croix : a / b = c / d ➔ a × d = b × c."
      ),
      estimatedMinutes = 25
    ),

    // 3AC S2: Fonctions linéaires et affines
    Lesson(
      id = "lesson_fonctions_3ac",
      chapterId = "3ac_s2_c6",
      level = SchoolLevel.AC3,
      title = "Fonctions linéaires et fonctions affines",
      chapterTitle = "Fonctions linéaires et affines",
      summary = "Notion d'image, d'antécédent, coefficient directeur, ordonnée à l'origine et représentations graphiques de droites.",
      tableOfContents = listOf(
        "1. Fonction linéaire : Définition et coefficient",
        "2. Fonction affine : Définition et propriétés",
        "3. Image et Antécédent",
        "4. Déterminer le coefficient a d'une fonction",
        "5. Représentation graphique d'une fonction affine"
      ),
      definitions = listOf(
        MathDefinition(
          term = "Fonction linéaire",
          explanation = "Une fonction qui modélise une situation de proportionnalité. Sa droite passe toujours par l'origine du repère O(0,0).",
          formula = "f(x) = ax  (où a est le coefficient de linéarité)"
        ),
        MathDefinition(
          term = "Fonction affine",
          explanation = "Une fonction dont la représentation graphique est une droite ne passant pas nécessairement par l'origine.",
          formula = "g(x) = ax + b  (a = coefficient directeur, b = ordonnée à l'origine)"
        )
      ),
      theorems = listOf(
        MathTheorem(
          title = "Formule du coefficient directeur",
          statement = "Si x₁ et x₂ sont deux nombres distincts, le coefficient directeur a de la fonction affine g est le taux d'accroissement :",
          formula = "a = [g(x₁) - g(x₂)] / (x₁ - x₂)",
          conditions = "Pour une fonction linéaire, simplement : a = f(x) / x (avec x ≠ 0)."
        )
      ),
      workedExamples = listOf(
        MathExample(
          title = "Calculer l'image et l'antécédent",
          problem = "Soit la fonction affine f(x) = 3x - 5. Calculer l'image de 4, puis l'antécédent de 7.",
          steps = listOf(
            "1. Image de 4 : on remplace x par 4 : f(4) = 3 × 4 - 5 = 12 - 5 = 7.",
            "2. Antécédent de 7 : on résout l'équation f(x) = 7.",
            "3. 3x - 5 = 7 ➔ 3x = 7 + 5 = 12 ➔ x = 12 / 3 = 4."
          ),
          finalAnswer = "Image de 4 = 7 ; Antécédent de 7 = 4"
        )
      ),
      diagramType = DiagramType.CARTESIAN_FUNCTION,
      commonMistakes = listOf(
        "Confondre image et antécédent : calculer l'image = remplacer x ; trouver l'antécédent = résoudre f(x) = y.",
        "Confondre le coefficient directeur a (pente de la droite) et l'ordonnée à l'origine b."
      ),
      keyPoints = listOf(
        "Fonction linéaire f(x) = ax passe par (0,0).",
        "Fonction affine g(x) = ax + b coupe l'axe vertical au point (0, b).",
        "Deux droites de même coefficient directeur a sont strictement parallèles."
      ),
      estimatedMinutes = 20
    ),

    // 1AC S1: Fractions
    Lesson(
      id = "lesson_fractions_1ac",
      chapterId = "1ac_s1_c2",
      level = SchoolLevel.AC1,
      title = "Fractions et opérations fondamentales",
      chapterTitle = "Fractions et opérations",
      summary = "Comprendre la notion de quotient, la simplification et les quatre opérations sur les fractions pour consolider les bases.",
      tableOfContents = listOf(
        "1. Notion de fraction et écriture",
        "2. Égalité et simplification",
        "3. Addition et soustraction au même dénominateur",
        "4. Réduction au même dénominateur",
        "5. Multiplication et division de fractions"
      ),
      definitions = listOf(
        MathDefinition(
          term = "Fraction a / b",
          explanation = "Une fraction représente le quotient exact de a par b (avec b ≠ 0). a est le numérateur et b est le dénominateur.",
          formula = "a / b = a ÷ b"
        )
      ),
      theorems = listOf(
        MathTheorem(
          title = "Simplification et égalité",
          statement = "La valeur d'une fraction ne change pas si l'on multiplie ou divise son numérateur et son dénominateur par un même nombre non nul k.",
          formula = "(a × k) / (b × k) = a / b"
        ),
        MathTheorem(
          title = "Multiplication de fractions",
          statement = "Pour multiplier deux fractions, on multiplie les numérateurs entre eux et les dénominateurs entre eux.",
          formula = "(a / b) × (c / d) = (a × c) / (b × d)"
        )
      ),
      workedExamples = listOf(
        MathExample(
          title = "Calculer 2/3 + 5/6",
          problem = "Effectuer l'addition et donner le résultat sous forme d'une fraction irréductible.",
          steps = listOf(
            "1. Réduire au même dénominateur (6 est multiple de 3) : 2/3 = (2×2)/(3×2) = 4/6.",
            "2. Additionner les numérateurs : 4/6 + 5/6 = 9/6.",
            "3. Simplifier par 3 : 9/6 = (3×3)/(2×3) = 3/2."
          ),
          finalAnswer = "3/2"
        )
      ),
      diagramType = DiagramType.FRACTIONS_BAR,
      commonMistakes = listOf(
        "Additionner les dénominateurs : 1/2 + 1/3 n'est JAMAIS égal à 2/5 !",
        "Oublier de simplifier le résultat final sous forme de fraction irréductible."
      ),
      keyPoints = listOf(
        "Pour additionner ou soustraire : TOUJOURS réduire d'abord au même dénominateur.",
        "Pour diviser par une fraction : multiplier par son inverse : (a/b) ÷ (c/d) = (a/b) × (d/c)."
      ),
      estimatedMinutes = 20
    )
  )

  val exercises: List<Exercise> = listOf(
    // Exercice 1 : Pythagore (QCM)
    Exercise(
      id = "ex_pythagore_1",
      chapterId = "3ac_s1_c3",
      chapterTitle = "Théorème de Pythagore",
      level = SchoolLevel.AC3,
      title = "Calcul d'hypoténuse dans un triangle rectangle",
      difficulty = ExerciseDifficulty.FACILE,
      type = ExerciseType.QCM,
      statement = "Soit un triangle RST rectangle en R tel que RS = 6 cm et RT = 8 cm. Quelle est la longueur de l'hypoténuse ST ?",
      mathExpression = "ST² = RS² + RT²",
      options = listOf("10 cm", "14 cm", "48 cm", "100 cm"),
      correctAnswerIndex = 0,
      hint = "Utilise le théorème de Pythagore : calcule ST² = 6² + 8², puis extrais la racine carrée.",
      stepByStepSolution = listOf(
        "1. Le triangle RST est rectangle en R, donc d'après Pythagore : ST² = RS² + RT².",
        "2. ST² = 6² + 8² = 36 + 64 = 100.",
        "3. ST = √100 = 10 cm."
      ),
      finalAnswer = "ST = 10 cm"
    ),

    // Exercice 2 : Pythagore (NUMERIC)
    Exercise(
      id = "ex_pythagore_2",
      chapterId = "3ac_s1_c3",
      chapterTitle = "Théorème de Pythagore",
      level = SchoolLevel.AC3,
      title = "Calcul d'un côté de l'angle droit",
      difficulty = ExerciseDifficulty.MOYEN,
      type = ExerciseType.NUMERIC,
      statement = "Dans un triangle ABC rectangle en B, on donne l'hypoténuse AC = 15 cm et AB = 9 cm. Calcule la longueur exacte du côté BC en cm.",
      mathExpression = "BC² = AC² - AB²",
      numericCorrectAnswer = 12.0,
      hint = "Ici l'inconnue est un côté de l'angle droit, donc tu dois soustraire : BC² = AC² - AB².",
      stepByStepSolution = listOf(
        "1. D'après le théorème de Pythagore : AC² = AB² + BC².",
        "2. On isole BC² : BC² = AC² - AB² = 15² - 9².",
        "3. 15² = 225 et 9² = 81 ➔ BC² = 225 - 81 = 144.",
        "4. BC = √144 = 12 cm."
      ),
      finalAnswer = "12"
    ),

    // Exercice 3 : Racines carrées (QCM)
    Exercise(
      id = "ex_racines_1",
      chapterId = "3ac_s1_c1",
      chapterTitle = "Racines carrées",
      level = SchoolLevel.AC3,
      title = "Simplification de radical",
      difficulty = ExerciseDifficulty.FACILE,
      type = ExerciseType.QCM,
      statement = "Laquelle des expressions suivantes est la forme simplifiée de √48 ?",
      mathExpression = "√48 = √(16 × 3)",
      options = listOf("4√3", "3√4", "16√3", "2√12"),
      correctAnswerIndex = 0,
      hint = "Cherche le plus grand carré parfait qui divise 48 (indice : 16 × 3 = 48).",
      stepByStepSolution = listOf(
        "1. 48 = 16 × 3.",
        "2. √48 = √(16 × 3) = √16 × √3.",
        "3. Comme √16 = 4, on obtient 4√3."
      ),
      finalAnswer = "4√3"
    ),

    // Exercice 4 : Identités remarquables (QCM)
    Exercise(
      id = "ex_identites_1",
      chapterId = "3ac_s1_c2",
      chapterTitle = "Développement et Identités remarquables",
      level = SchoolLevel.AC3,
      title = "Développer un carré de différence",
      difficulty = ExerciseDifficulty.MOYEN,
      type = ExerciseType.QCM,
      statement = "Développer l'expression : (3x - 5)²",
      mathExpression = "(a - b)² = a² - 2ab + b²",
      options = listOf("9x² - 30x + 25", "9x² - 25", "9x² + 30x + 25", "3x² - 15x + 25"),
      correctAnswerIndex = 0,
      hint = "Applique (a - b)² = a² - 2ab + b² avec a = 3x et b = 5.",
      stepByStepSolution = listOf(
        "1. a = 3x et b = 5.",
        "2. a² = (3x)² = 9x².",
        "3. 2ab = 2 × (3x) × 5 = 30x.",
        "4. b² = 5² = 25.",
        "5. Conclusion : 9x² - 30x + 25."
      ),
      finalAnswer = "9x² - 30x + 25"
    ),

    // Exercice 5 : Thalès (NUMERIC)
    Exercise(
      id = "ex_thales_1",
      chapterId = "3ac_s1_c4",
      chapterTitle = "Théorème de Thalès",
      level = SchoolLevel.AC3,
      title = "Application du produit en croix de Thalès",
      difficulty = ExerciseDifficulty.MOYEN,
      type = ExerciseType.NUMERIC,
      statement = "Dans un triangle ABC, M ∈ [AB] et N ∈ [AC] avec (MN) // (BC). Sachant que AM = 3 cm, AB = 9 cm et BC = 15 cm, quelle est la longueur de MN en cm ?",
      mathExpression = "AM / AB = MN / BC",
      numericCorrectAnswer = 5.0,
      hint = "Écris le rapport : AM / AB = MN / BC ➔ 3 / 9 = MN / 15.",
      stepByStepSolution = listOf(
        "1. Les hypothèses de Thalès sont vérifiées : AM / AB = MN / BC.",
        "2. Remplacement : 3 / 9 = MN / 15.",
        "3. On sait que 3/9 = 1/3, donc MN = 15 / 3 = 5 cm."
      ),
      finalAnswer = "5"
    ),

    // Exercice 6 : Vrai ou Faux (Fonction linéaire)
    Exercise(
      id = "ex_fonctions_vf",
      chapterId = "3ac_s2_c6",
      chapterTitle = "Fonctions linéaires et affines",
      level = SchoolLevel.AC3,
      title = "Propriété de la droite d'une fonction linéaire",
      difficulty = ExerciseDifficulty.FACILE,
      type = ExerciseType.TRUE_FALSE,
      statement = "La représentation graphique de toute fonction linéaire f(x) = ax passe obligatoirement par l'origine du repère O(0,0).",
      mathExpression = "f(0) = a × 0 = 0",
      options = listOf("Vrai", "Faux"),
      correctAnswerIndex = 0,
      trueFalseCorrect = true,
      hint = "Calcule l'image de 0 par f : f(0) = a × 0 = ?",
      stepByStepSolution = listOf(
        "1. Pour toute fonction linéaire f(x) = ax, l'image de 0 est f(0) = a × 0 = 0.",
        "2. Le point (0,0) appartient donc systématiquement à la droite représentative.",
        "3. La proposition est donc Vraie."
      ),
      finalAnswer = "Vrai"
    ),

    // Exercice 7 : Fractions 1AC (QCM)
    Exercise(
      id = "ex_fractions_1ac",
      chapterId = "1ac_s1_c2",
      chapterTitle = "Fractions et opérations",
      level = SchoolLevel.AC1,
      title = "Addition de fractions avec réduction",
      difficulty = ExerciseDifficulty.FACILE,
      type = ExerciseType.QCM,
      statement = "Calculer la somme suivante sous forme irréductible : 3/4 + 1/8",
      mathExpression = "3/4 + 1/8 = 6/8 + 1/8",
      options = listOf("7/8", "4/12", "4/8", "1/2"),
      correctAnswerIndex = 0,
      hint = "Réduis au dénominateur commun 8 en multipliant le numérateur et le dénominateur de 3/4 par 2.",
      stepByStepSolution = listOf(
        "1. 3/4 = (3×2)/(4×2) = 6/8.",
        "2. 6/8 + 1/8 = (6+1)/8 = 7/8.",
        "3. 7/8 est irréductible."
      ),
      finalAnswer = "7/8"
    )
  )

  val regionalExams: List<RegionalExam> = listOf(
    RegionalExam(
      id = "exam_casablanca_2024",
      year = 2024,
      region = "Casablanca-Settat",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Examen Régional Normalisé — Session Normale Juin 2024",
      duration = "2 heures",
      coefficient = 3,
      exercises = listOf(
        ExamExerciseSection(
          exerciseNumber = "Exercice 1",
          points = "5 points",
          theme = "Équations, Inéquations et Systèmes",
          questions = listOf(
            "1. a) Résoudre l'équation : 3x - 5 = x + 7.",
            "1. b) Résoudre l'équation : (2x - 3)(x + 4) = 0.",
            "2. Résoudre l'inéquation : 5x - 2 ≥ 2x + 7 et représenter les solutions sur une droite graduée.",
            "3. Résoudre dans ℝ² le système suivant : { 2x + 3y = 13 ; x - y = 4 }."
          ),
          detailedCorrection = listOf(
            "1. a) 3x - x = 7 + 5 ➔ 2x = 12 ➔ x = 6. L'ensemble des solutions est S = {6}.",
            "1. b) Un produit de facteurs est nul si l'un au moins des facteurs est nul : 2x - 3 = 0 ou x + 4 = 0 ➔ x = 3/2 ou x = -4. S = {-4 ; 3/2}.",
            "2. 5x - 2x ≥ 7 + 2 ➔ 3x ≥ 9 ➔ x ≥ 3. Les solutions sont tous les nombres réels supérieurs ou égaux à 3.",
            "3. Par substitution de la 2ème équation : x = y + 4. On remplace dans la 1ère : 2(y + 4) + 3y = 13 ➔ 2y + 8 + 3y = 13 ➔ 5y = 5 ➔ y = 1. Donc x = 1 + 4 = 5. Le couple solution est (5, 1)."
          )
        ),
        ExamExerciseSection(
          exerciseNumber = "Exercice 2",
          points = "4 points",
          theme = "Fonctions linéaires et Fonctions affines",
          questions = listOf(
            "1. Soit f la fonction linéaire telle que f(3) = 6. Montrer que f(x) = 2x.",
            "2. Soit g la fonction affine définie par g(x) = -x + 4. Calculer g(2) et déterminer l'antécédent de 0.",
            "3. Construire les représentations graphiques (D₁) et (D₂) de f et g dans un repère orthonormé (O, I, J).",
            "4. Déterminer graphiquement les coordonnées du point d'intersection de (D₁) et (D₂)."
          ),
          detailedCorrection = listOf(
            "1. f est linéaire donc f(x) = ax. a = f(3)/3 = 6/3 = 2. Donc f(x) = 2x.",
            "2. g(2) = -2 + 4 = 2. Antécédent de 0 : g(x) = 0 ➔ -x + 4 = 0 ➔ x = 4.",
            "3. (D₁) passe par (0,0) et (3,6). (D₂) passe par (0,4) et (4,0).",
            "4. Au point d'intersection, f(x) = g(x) ➔ 2x = -x + 4 ➔ 3x = 4 ➔ x = 4/3. L'ordonnée est y = 2(4/3) = 8/3. Point d'intersection : E(4/3, 8/3)."
          )
        ),
        ExamExerciseSection(
          exerciseNumber = "Exercice 3",
          points = "5 points",
          theme = "Repère dans le plan et Équation de droite",
          questions = listOf(
            "Dans le plan muni d'un repère orthonormé (O, I, J), on considère les points A(1, 2), B(3, 6) et C(5, 0).",
            "1. Calculer les coordonnées du vecteur AB et la distance AB.",
            "2. Déterminer les coordonnées du point M, milieu du segment [AC].",
            "3. Montrer que l'équation réduite de la droite (AB) est y = 2x.",
            "4. Déterminer l'équation de la droite (Δ) passant par C et perpendiculaire à (AB)."
          ),
          detailedCorrection = listOf(
            "1. Vecteur AB(xB - xA, yB - yA) = AB(3 - 1, 6 - 2) = AB(2, 4). Distance AB = √(2² + 4²) = √(4 + 16) = √20 = 2√5.",
            "2. M((xA + xC)/2, (yA + yC)/2) = M((1 + 5)/2, (2 + 0)/2) = M(3, 1).",
            "3. Pente m = (6 - 2)/(3 - 1) = 4/2 = 2. y = 2x + p. Comme A(1,2) ∈ (AB) : 2 = 2(1) + p ➔ p = 0. Donc y = 2x.",
            "4. (Δ) ⊥ (AB) donc m' × 2 = -1 ➔ m' = -1/2. Équation : y = -1/2 x + p'. Comme C(5,0) ∈ (Δ) : 0 = -1/2(5) + p' ➔ p' = 5/2. Donc y = -1/2 x + 5/2."
          )
        ),
        ExamExerciseSection(
          exerciseNumber = "Exercice 4",
          points = "3 points",
          theme = "Statistiques",
          questions = listOf(
            "Le tableau suivant donne les notes obtenues par 25 élèves lors d'un devoir de mathématiques :",
            "Notes : [8, 10, 12, 14, 16] — Effectifs : [3, 7, 8, 5, 2].",
            "1. Déterminer le mode et la médiane de cette série statistique.",
            "2. Calculer la moyenne arithmétique de la classe."
          ),
          detailedCorrection = listOf(
            "1. Le plus grand effectif est 8, associé à la note 12. Le mode est donc 12. L'effectif total est N = 25 (impair). La médiane est la (25+1)/2 = 13ème valeur. En calculant les effectifs cumulés [3, 10, 18], la 13ème valeur est 12. Médiane = 12.",
            "2. Moyenne M = (8×3 + 10×7 + 12×8 + 14×5 + 16×2) / 25 = (24 + 70 + 96 + 70 + 32) / 25 = 292 / 25 = 11.68 / 20."
          )
        ),
        ExamExerciseSection(
          exerciseNumber = "Exercice 5",
          points = "3 points",
          theme = "Géométrie dans l'espace",
          questions = listOf(
            "Soit SABCD une pyramide régulière à base carrée ABCD de côté 6 cm et de hauteur SO = 8 cm.",
            "1. Calculer le volume V₁ de la pyramide SABCD.",
            "2. On réalise une réduction de rapport k = 1/2. Calculer le volume V₂ de la pyramide réduite."
          ),
          detailedCorrection = listOf(
            "1. Aire de la base B = 6 × 6 = 36 cm². Volume V₁ = (1/3) × B × SO = (1/3) × 36 × 8 = 12 × 8 = 96 cm³.",
            "2. Par réduction de rapport k, les volumes sont multipliés par k³. V₂ = k³ × V₁ = (1/2)³ × 96 = (1/8) × 96 = 12 cm³."
          )
        )
      )
    ),

    RegionalExam(
      id = "exam_rabat_2023",
      year = 2023,
      region = "Rabat-Salé-Kénitra",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Examen Régional Normalisé — Session Normale Juin 2023",
      duration = "2 heures",
      coefficient = 3,
      exercises = listOf(
        ExamExerciseSection(
          exerciseNumber = "Exercice 1",
          points = "6 points",
          theme = "Algèbre & Systèmes",
          questions = listOf(
            "1. Résoudre l'équation : 4x - (x + 2) = 7.",
            "2. Résoudre : (3x + 1)² = (x - 2)².",
            "3. Résoudre le système par combinaison linéaire : { 3x + 2y = 12 ; 5x - y = 7 }."
          ),
          detailedCorrection = listOf(
            "1. 4x - x - 2 = 7 ➔ 3x = 9 ➔ x = 3. S = {3}.",
            "2. (3x + 1)² - (x - 2)² = 0 ➔ [(3x + 1) - (x - 2)][(3x + 1) + (x - 2)] = 0 ➔ (2x + 3)(4x - 1) = 0 ➔ x = -3/2 ou x = 1/4. S = {-3/2 ; 1/4}.",
            "3. Multiplier la 2ème équation par 2 : 10x - 2y = 14. Additionner à la 1ère : (3x + 10x) = 12 + 14 ➔ 13x = 26 ➔ x = 2. Remplacer dans 5(2) - y = 7 ➔ y = 10 - 7 = 3. Solution : (2, 3)."
          )
        ),
        ExamExerciseSection(
          exerciseNumber = "Exercice 2",
          points = "6 points",
          theme = "Vecteurs & Translation",
          questions = listOf(
            "Soit ABCD un parallélogramme de centre O.",
            "1. Construire le point E tel que vecteur AE = vecteur AB + vecteur AC.",
            "2. Déterminer l'image du point D par la translation de vecteur AB.",
            "3. Simplifier : vecteur AB + vecteur BC + vecteur CD."
          ),
          detailedCorrection = listOf(
            "1. On utilise la règle du parallélogramme à partir des vecteurs AB et AC pour placer E.",
            "2. Comme ABCD est un parallélogramme, vecteur AB = vecteur DC. Donc l'image de D par la translation de vecteur AB est le point C.",
            "3. D'après la relation de Chasles : AB + BC + CD = AC + CD = AD."
          )
        ),
        ExamExerciseSection(
          exerciseNumber = "Exercice 3",
          points = "8 points",
          theme = "Fonctions & Repère orthonormé",
          questions = listOf(
            "1. Déterminer l'expression de la fonction affine f sachant que f(1) = 3 et f(4) = 9.",
            "2. Calculer le coefficient directeur et l'ordonnée à l'origine.",
            "3. Les points K(2, 5) et L(-1, -1) appartiennent-ils à la droite (D) représentant f ?"
          ),
          detailedCorrection = listOf(
            "1. a = (9 - 3)/(4 - 1) = 6/3 = 2. f(x) = 2x + b. f(1) = 2(1) + b = 3 ➔ b = 1. Donc f(x) = 2x + 1.",
            "2. Coefficient directeur a = 2, ordonnée à l'origine b = 1.",
            "3. f(2) = 2(2) + 1 = 5 ➔ K ∈ (D). f(-1) = 2(-1) + 1 = -1 ➔ L ∈ (D). Les deux points appartiennent à (D)."
          )
        )
      )
    ),

    RegionalExam(
      id = "exam_marrakech_2023",
      year = 2023,
      region = "Marrakech-Safi",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Examen Régional Normalisé — Session Normale 2023",
      duration = "2 heures",
      coefficient = 3,
      exercises = listOf(
        ExamExerciseSection(
          exerciseNumber = "Exercice 1",
          points = "5 points",
          theme = "Équations & Inéquations",
          questions = listOf(
            "1. Résoudre dans ℝ : 2(x - 3) + 5 = 3x - 4.",
            "2. Résoudre dans ℝ : 4x² - 9 = 0.",
            "3. Résoudre l'inéquation : (3x - 1)/2 ≤ (x + 3)/3."
          ),
          detailedCorrection = listOf(
            "1. 2x - 6 + 5 = 3x - 4 ➔ 2x - 1 = 3x - 4 ➔ -x = -3 ➔ x = 3. S = {3}.",
            "2. (2x - 3)(2x + 3) = 0 ➔ x = 3/2 ou x = -3/2. S = {-3/2 ; 3/2}.",
            "3. Multiplier les deux membres par 6 (positif) : 3(3x - 1) ≤ 2(x + 3) ➔ 9x - 3 ≤ 2x + 6 ➔ 7x ≤ 9 ➔ x ≤ 9/7."
          )
        )
      )
    ),

    RegionalExam(
      id = "exam_fes_2024",
      year = 2024,
      region = "Fès-Meknès",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Examen Régional Normalisé — Juin 2024",
      duration = "2 heures",
      coefficient = 3,
      exercises = listOf(
        ExamExerciseSection(
          exerciseNumber = "Exercice 1",
          points = "6 points",
          theme = "Systèmes & Fonctions",
          questions = listOf(
            "1. Résoudre le système { x + y = 20 ; 2x + 4y = 56 }.",
            "2. Problème : Un fermier possède des poules (2 pattes) et des lapins (4 pattes). Au total, il compte 20 têtes et 56 pattes. Déterminer le nombre de poules et de lapins."
          ),
          detailedCorrection = listOf(
            "1. De la 1ère équation, x = 20 - y. On remplace dans la 2ème : 2(20 - y) + 4y = 56 ➔ 40 - 2y + 4y = 56 ➔ 2y = 16 ➔ y = 8. Donc x = 20 - 8 = 12. Solution : (12, 8).",
            "2. Soit x le nombre de poules et y le nombre de lapins. Total têtes : x + y = 20. Total pattes : 2x + 4y = 56. Ce système correspond exactement à la question 1. Il y a donc 12 poules et 8 lapins."
          )
        )
      )
    ),

    RegionalExam(
      id = "exam_tanger_2023",
      year = 2023,
      region = "Tanger-Tétouan-Al Hoceïma",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Examen Régional Normalisé — Juin 2023",
      duration = "2 heures",
      coefficient = 3,
      exercises = listOf(
        ExamExerciseSection(
          exerciseNumber = "Exercice 1",
          points = "5 points",
          theme = "Repère et Équation de droite",
          questions = listOf(
            "1. Placer les points A(2, 3) et B(-2, 1) dans un repère orthonormé.",
            "2. Déterminer le coefficient directeur de la droite (AB).",
            "3. Trouver l'équation de la droite parallèle à (AB) passant par l'origine."
          ),
          detailedCorrection = listOf(
            "1. Points placés dans le repère.",
            "2. a = (1 - 3) / (-2 - 2) = (-2) / (-4) = 1/2.",
            "3. Droite parallèle donc même coefficient directeur m = 1/2. Passant par l'origine O(0,0) donc ordonnée à l'origine b = 0. Équation : y = 1/2 x."
          )
        )
      )
    ),

    RegionalExam(
      id = "exam_souss_2024",
      year = 2024,
      region = "Souss-Massa",
      level = SchoolLevel.AC3,
      semester = Semester.S2,
      title = "Examen Régional Normalisé — Session Juin 2024",
      duration = "2 heures",
      coefficient = 3,
      exercises = listOf(
        ExamExerciseSection(
          exerciseNumber = "Exercice 1",
          points = "4 points",
          theme = "Statistiques & Probabilités",
          questions = listOf(
            "1. Calculer la moyenne d'une série statistique de valeurs pondérées.",
            "2. Interpréter la valeur de la médiane dans le contexte du brevet."
          ),
          detailedCorrection = listOf(
            "1. Somme des produits (valeur × effectif) divisée par l'effectif total.",
            "2. 50% au moins des élèves ont obtenu une note inférieure ou égale à la médiane."
          )
        )
      )
    )
  )

  val moroccanRegions = listOf(
    "Toutes les régions",
    "Casablanca-Settat",
    "Rabat-Salé-Kénitra",
    "Marrakech-Safi",
    "Fès-Meknès",
    "Tanger-Tétouan-Al Hoceïma",
    "Souss-Massa",
    "Béni Mellal-Khénifra",
    "Drâa-Tafilalet",
    "Oriental",
    "Guelmim-Oued Noun",
    "Laâyoune-Sakia El Hamra",
    "Dakhla-Oued Ed-Dahab"
  )
}

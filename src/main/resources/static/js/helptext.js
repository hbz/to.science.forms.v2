function enablePopover() {
	$('button[name="rdfType"]').webuiPopover({
	    title:'Publikationstyp',
	    content:'Form der Veröffentlichung<br />Statische Vorschlagsliste | Pflichtfeld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="publicationStatus"]').webuiPopover({
	    title:'Publikationsstatus',
	    content:'Version der Veröffentlichung<br />Informationen zu Preprint- und Postprint-Versionen finden Sie <a href="https://www.publisso.de/open-access-beraten/faqs/preprint-und-postprint-version/">hier</a>.<br />Statische Vorschlagsliste | Pflichtfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="reviewStatus"]').webuiPopover({
	    title:'Begutachtungsstatus',
	    content:'Prüfstatus der Veröffentlichung<br />Informationen zum Peer Review finden Sie <a href="">hier</a>.<br />Statische Vorschlagsliste',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="title"]').webuiPopover({
	    title:'Titel',
	    content:'Übernahme des Titels nach Vorlage, inklusive möglicher Untertitel u.a. Titelzusätze<br />Beispiel: Fruiting Africa Baseline Survey - Tools and Datasets<br />Vergrößerbares Freitextfeld | Pflichtfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="alternative"]').webuiPopover({
	    title:'Alternativer Titel',
	    content:'Angabe eines Parallel- oder Übersetzungstitels nach Vorlage, inklusive möglicher Untertitel u.a. Titelzusätze<br />Beispiel: Titel: "PUBLISSO: Das Open-Access-Publikationsportal für die Lebenswissenschaften" | Alternativer Titel: "PUBLISSO: an open access publication portal for life sciences"<br />Vergrößerbares Freitextfeld',
	    trigger: 'hover',
	    width:'400px'
	    
	});
	
	$('button[name="creator"]').webuiPopover({
	    title:'Autor/in',
	    content:'Personen, die den Inhalt der Veröffentlichung erarbeitet haben. Für jede zu nennende Person muss eine eigene Position in der Maske belegt werden.<br />Linked-Data-Feld | Mehrfachnennung möglich<br />Lokal: Keine Verknüpfung mit einem Pool. Ansetzung: Nachname, Vorname. Beispiel: Arning, Ursula<br />ORCiD: Verknüpfung zum <a href="https://orcid.org/">ORCiD-Profil der Person</a>. Beispiel: 0000-0002-7953-0666<br />GND (Person/Körperschaft): <a href="https://www.dnb.de/DE/Standardisierung/GND/gnd_node.html">Verknüpfung zum GND-Eintrag der Person/Körperschaft</a>. Beispiel: 1072011352',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="creatorName"]').webuiPopover({
	    title:'Autor/in (als Freitext)',
	    content:'Angabe der Familien- und Vornamen der Verfasser*innen oder einer Körperschaft (falls diese Autorin ist). Dieses Feld ist dann auszufüllen, falls die jeweilige Person bzw. Institution weder in der GND noch über OrcID (Open Researcher and Contributor ID - Also ein eindeutiger Identifikator für Personen und Institutionen aus der wissenschaftlichen Publikationstätigkeit) auffindbar ist.',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="contributor"]').webuiPopover({
	    title:'Mitwirkende/r',
	    content:'Personen, die die Autor*innen bei der Erarbeitung des Inhalts unterstützt haben. Für jede zu nennende Person muss eine eigene Position in der Maske belegt werden.<br />Linked-Data-Feld | Mehrfachnennung möglich<br />Lokal: Keine Verknüpfung mit einem Pool. Ansetzung: Nachname, Vorname. Beispiel: Arning, Ursula<br />ORCiD: Verknüpfung zum <a href="https://orcid.org/">ORCiD-Profil der Person</a>. Beispiel: 0000-0002-7953-0666<br />GND (Person/Körperschaft): <a href="https://www.dnb.de/DE/Standardisierung/GND/gnd_node.html">Verknüpfung zum GND-Eintrag der Person/Körperschaft</a>. Beispiel: 1072011352',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="contributorName"]').webuiPopover({
	    title:'Mitwirkender (als Freitext)',
	    content:'Angabe der Namen von Personen, die z.B. an der Datenerhebung beteiligt waren, jedoch nicht als Autoren erscheinen. Dieses Feld kann verwendet werden, falls die jeweilige beteiligte Person weder über die GND noch via Orcid identifizierbar ist.',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="editor"]').webuiPopover({
	    title:'(Academic) Editor',
	    content:'Personen, die als Redakteure/Lektoren an der Veröffentlichung mitgearbeitet haben. Für jede zu nennende Person muss eine eigene Position in der Maske belegt werden<br />Linked-Data-Feld | Mehrfachnennung möglich<br />Lokal: Keine Verknüpfung mit einem Pool. Ansetzung: Nachname, Vorname. Beispiel: Arning, Ursula<br />ORCiD: Verknüpfung zum <a href="https://orcid.org/">ORCiD-Profil der Person</a>. Beispiel: 0000-0002-7953-0666<br />GND (Person/Körperschaft): <a href="https://www.dnb.de/DE/Standardisierung/GND/gnd_node.html">Verknüpfung zum GND-Eintrag der Person/Körperschaft</a>. Beispiel: 1072011352',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="other"]').webuiPopover({
	    title:'Sonstige',
	    content:'Sonstige Personen, die an der Veröffentlichung mitgearbeitet haben. Für jede zu nennende Person muss eine eigene Position in der Maske belegt werden.<br />Linked-Data-Feld | Mehrfachnennung möglich<br />Lokal: Keine Verknüpfung mit einem Pool. Ansetzung: Nachname, Vorname. Beispiel: Arning, Ursula<br />ORCiD: Verknüpfung zum <a href="https://orcid.org/">ORCiD-Profil der Person</a>. Beispiel: 0000-0002-7953-0666<br />GND (Person/Körperschaft): <a href="https://www.dnb.de/DE/Standardisierung/GND/gnd_node.html">Verknüpfung zum GND-Eintrag der Person/Körperschaft</a>. Beispiel: 1072011352',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="containedIn"]').webuiPopover({
	    title:'Erschienen in',
	    content:'Angabe der Quelle, welche die zu erfassende Veröffentlichung beinhaltet<br />Linked-Data-Feld | Pflichtfeld | Mehrfachnennung möglich<br />Zeitschrift: Suche nach ZDB-Titeln im hbz-Verbundkatalog. Sucheinstiege: Titel, ISSN (bitte ohne Bindestrich suchen!), <a href="http://okeanos-www.hbz-nrw.de/F/">ZDB-ID</a><br />Buch: Suche nach Monographien im hbz-Verbundkatalog. Sucheinstiege: Titel, ISBN<br />Kongress: Suche nach Kongressansetzungen in der <a href="https://www.dnb.de/DE/Standardisierung/GND/gnd_node.html">GND</a><br />Monogr. ÜO: Suche nach monographischen Schriftenreihen im hbz-Verbundkatalog<br />Lokal: Keine Verknüpfung mit einem der genannten Pools<br />Aleph: Suche im gesamten hbz-Verbundkatalog. Sucheinstieg über HT-Nummer empfohlen',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="bibliographicCitation"]').webuiPopover({
	    title:'Quellenangabe',
	    content:'Angabe von Band, Heft und Seiten/Artikelnummer nach dem <a href="https://help.citavi.com/knowledge-base/article/vancouver">Vancouver-Zitationsstil</a><br />Beispiel: 57(2):218–259 oder 16(4):e1002780. Auch möglich: Advanced Access, Early View etc.<br />Freitextfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="publicationYear"]').webuiPopover({
	    title:'Online veröffentlicht',
	    content:'Frühestes Veröffentlichungsdatum im Internet<br />Pflichtfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="institution"]').webuiPopover({
	    title:'FRL-Sammlung',
	    content:'Speisung der <a href="https://repository.publisso.de/browse/institution">FRL-Sammlung für kooperierende Institutionen/Personen</a><br />Linked-Data-Feld | Mehrfachnennung möglich<br />GND (Körperschaften/Personen): Suche nach Ansetzungen in der <a href="https://www.dnb.de/DE/Standardisierung/GND/gnd_node.html">GND</a><br />Lokal: Keine Verknüpfung mit einem der genannten Pools',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="collectionTwo"]').webuiPopover({
	    title:'Übergeordneter Kongress',
	    content:'Speisung der FRL-Sammlung für Kongresse<br />Linked-Data-Feld | Mehrfachnennung möglich<br />GND (Kongress): Suche nach Ansetzungen in der <a href="https://www.dnb.de/DE/Standardisierung/GND/gnd_node.html">GND</a>.<br />Lokal: Keine Verknüpfung mit einem der genannten Pools',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="collectionOne"]').webuiPopover({
	    title:'LeibnizOpem',
	    content:'Freigabe der Veröffentlichung für die Lieferung nach <a href="http://www.leibnizopen.de/home">LeibnizOpen</a>, dem zentralen Open-Access-Portal der Leibniz-Gemeinschaft. Diese Option darf nur für Veröffentlichungen gewählt werden, an deren Forschungsgrundlagen, Erstellung etc. mindestens eine Person aus einem Leibniz-Institut beteiligt war<br />Statische Vorschlagsliste mit Multiselect-Widget | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="medium"]').webuiPopover({
	    title:'Art der Datei',
	    content:'Angabe des Medientyps, in der der Inhalt dargestellt wird.<br />Statische Vorschlagsliste | Pflichtfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="yearOfCopyright"]').webuiPopover({
	    title:'CopyrightJahr',
	    content:'Das Copyrightjahr muss nur angegeben werden, wenn es in der Veröffentlichung genannt wird (mit © oder "Copyright" gekennzeichnet)<br />Statische Auswahlliste | Wahl des Jahres mit Kalender-Jahres-Widget',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="license"]').webuiPopover({
	    title:'Lizenz',
	    content:'<b>Forschungsdaten:</b> Angabe der Lizenz unter der die Daten veröffentlicht werden sollen. Es wird empfohlen, die ODC BY-Lizenz zu verwenden. Es sind aber auch andere Open-Content-Lizenzen möglich.Nachfolgend finden Sie links zu den Texten der möglichen Lizenzen:<ul><li><a href="http://opendatacommons.org/licenses/by/1-0/">ODC BY</a></li><li><a href="https://creativecommons.org/licenses/by/4.0/">CC BY 4.0</a></li><li><a href="https://creativecommons.org/publicdomain/zero/1.0/">CC0 1.0</a></li>	<li><a href="http://opendatacommons.org/licenses/odbl/1.0/">ODbL</a></li><li><a href="http://opendatacommons.org/licenses/pddl/1.0/">PDDL 1.0</a></li>	<li><a href="https://www.gnu.org/licenses/gpl-3.0.de.html">GNU GPL 3.0</a></li></ul>Statische Vorschlagsliste | Pflichtfeld<br /><b>Andere Publikationen:</b> Link zu der vom Urheber/Verlag vergebenen Veröffentlichungslizenz. Es kann sich dabei sowohl um einen Link zu einer Open-Access-Lizenz handeln (z.B. Creative-Commons-Lizenz), als auch um einen Link zur Policy des Verlages, in welcher das Zweitveröffentlichungsrecht für fachspezifische Repositorien eingeräumt wird. In dieses Feld muss stets ein vollständiger Link eingetragen werden, beginnend mit http:// oder https://<br />Beispiel: <a href="http://creativecommons.org/licenses/by/4.0/">http://creativecommons.org/licenses/by/4.0/</a><br />Freitextfeld (Linkfeld)',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="embargoTime"]').webuiPopover({
	    title:'Embargo',
	    content:'Angabe des Datums, an dem ein evtl. Embargo endet. Bei der Freigabe durch ZB MED werden zunächst nur die Metadaten im FRL sichtbar geschaltet. Für Forschungsdaten werden Embargos bis maximal 2 Jahre akzeptiert.<br />Wahl des Datums mit Kalender-Widget',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="description').webuiPopover({
	    title:'Beschreibung',
	    content:'Beschreibung des Inhalts, der verwendeten Parameter und sonstiger Informationen zum Forschungsgegenstand. Die Beschreibung darf in Deutsch, Englisch, Französisch, Italienisch oder Spanisch verfasst sein.<br />Vergrößerbares Freitextfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="usageManual').webuiPopover({
	    title:'Hinweise zur Nutzung',
	    content:'Angabe der Informationen, welche zur (besseren) Nachnutzung der Daten wichtig sind. Dazu gehören vor Allem Informationen zu Feldbeschreibungen, Abkürzungen und Maßeinheiten, die verwendet wurden. Möglich sind aber auch Informationen, die über die Angaben im Feld "Medium" und "Erfassungsart" hinaus gehen und für die Nutzung wichtig sein können. Es ist nicht erforderlich, sämtliche Informationen dazu hier einzutragen, wenn diese an anderer Stelle auffindbar sind. in diesem Fall reicht es aus, diesen Ort zu benennen (z.B. readme-Datei/Reiter "Feldbeschreibung" in Excel-Datei).<br />Vergrößerbares Freitextfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="abstractText_0').webuiPopover({
	    title:'Abstract/Summary',
	    content:'Wird von der Verlagsseite vorlagegemäß übernommen. Beinhaltet das Abstract Zwischenüberschriften, so werden diese in Großbuchstaben umgeschrieben und per Doppelpunkt vom restlichen Text abgesetzt. Zeilenumbrüche werden auf der Oberfläche nicht angezeigt und können bei der Weitergabe in andere Systeme zu fehlerhaften Darstellungen führen. Bitte daher das Abstract ohne Zeilenumbrüche übernehmen (z.B. nicht aus einem PDF herauskopieren, sondern von der HTML-Seite des Verlags)<br />Beispiel: "BACKGROUND: Lipoarabinomannan (LAM) is a major antigen of Mycobacterium tuberculosis (MTB). [...]"<br />Vergrößerbares Freitextfeld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="language').webuiPopover({
	    title:'Sprache der Publikation',
	    content:'Angabe der Sprache, welche bei der Publikation verwendet wurde<br />Statische Auswahlliste | Pflichtfeld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="professionalGroup').webuiPopover({
	    title:'Fachgruppe',
	    content:'Angabe der Fachdisziplin für die die Daten (ursprünglich) gedacht sind. Sollten mehrere Fachgruppen zutreffen, sollte die primäre gewählt werden.<br />Statische Auswahlliste | Pflichtfeld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="ddc').webuiPopover({
	    title:'Fächerklassifikation (DDC)',
	    content:'Eine Auswahl der <a href="https://www.dnb.de/DE/Professionell/DDC-Deutsch/ddc-deutsch_node.html">Dewey-Dezimalklassifikation</a> zur inhaltlichen Beschreibung der Veröffentlichung<br />Statische Auswahlliste mit Multiselect-Widget | Pflichtfeld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="subject').webuiPopover({
	    title:'Sacherschließung',
	    content:'Prägnante Begriffe zur inhaltlichen Beschreibung. Sie können mit der <a href="https://www.dnb.de/DE/Standardisierung/GND/gnd_node.html">GND</a> und <a href="http://aims.fao.org/vest-registry/vocabularies/agrovoc">Agrovoc</a> verknüpft werden.<br />Beispiel: Immunologie<br />Linked-Data-Feld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="publisherVersion').webuiPopover({
	    title:'Verlagsversion',
	    content:'Link zur Originalquelle der Veröffentlichung. Existiert ein Persistenter Link/Identifikator (DOI, URN etc.), wird dieser bevorzugt übernommen. In dieses Feld muss stets ein vollständiger Link eingetragen werden, beginnend mit http:// oder https://<br />Beispiel: <a href="https://doi.org/10.1371/journal.pmed.1002780">https://doi.org/10.1371/journal.pmed.1002780</a><br />Freitextfeld (Linkfeld) | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	    
	});
	
	$('button[name="fulltextVersion').webuiPopover({
	    title:'Volltext',
	    content:'Direkter Link zum Volltext. Dieses Feld wird nur verwendet, wenn es keine vorgeschaltete Verlagsseite gibt und der anzugebende Link direkt zum Volltext führt. Existiert ein Persistenter Link/Identifikator (DOI, URN etc.), wird dieser bevorzugt übernommen. In dieses Feld muss stets ein vollständiger Link eingetragen werden, beginnend mit http:// oder https://<br />Freitextfeld (Linkfeld) | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="additionalMaterial').webuiPopover({
	    title:'Ergänzendes Material',
	    content:'Werden vom Verlag weiterführende Materialien geliefert, so kann an dieser Stelle darauf verlinkt werden. Dabei sollte möglichst ein übergeordneter Link genutzt werden, statt jede Datei des ergänzenden Materials einzeln zu verlinken. Existiert ein Persistenter Link/Identifikator (DOI, URN etc.), wird dieser bevorzugt übernommen. In dieses Feld muss stets ein vollständiger Link eingetragen werden, beginnend mit http:// oder https://<br />Beispiel: <a href="https://journals.plos.org/plosmedicine/article?id=10.1371/journal.pmed.1002780#sec021">https://journals.plos.org/plosmedicine/article?id=10.1371/journal.pmed.1...</a><br />Freitextfeld (Linkfeld) | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="internalReference').webuiPopover({
	    title:'Interne Referenz',
	    content:'Soll im FRL zwischen verschiedenen Ressourcen verlinkt werden, so kann dies über dieses Feld realisiert werden. Dabei wird die stabile FRL-Adresse der Ressource übernommen. In dieses Feld muss stets ein vollständiger Link eingetragen werden, beginnend mit http:// oder https://<br />Beispiel: <a href="https://repository.publisso.de/resource/frl%3A6405454">https://repository.publisso.de/resource/frl%3A6405454</a><br />Freitextfeld (Linkfeld) | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="funding').webuiPopover({
	    title:'Förderer',
	    content:'Angabe der Instanz, welche die Veröffentlichung finanziell unterstützt hat.<br />Beispiel: European Union<br />Crossref Funder Registry: Verknüpfung mit Förderer-Profilen der <a href="https://www.crossref.org/services/funder-registry/">Funder Registry von Crossref</a>. Es werden alternative/übersetzte Namen und weitere Informationen in dem Profil gespeichert und per DOI abgesichert.<br />Lokal: Keine Verknüpfung mit einem Pool<br />Linked-Data-Feld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="projectId').webuiPopover({
	    title:'Fördernummer',
	    content:'Angabe der Vorgangsnummer der Finanzierung. Einleitende Worte wie „Grant number“ o.ä. werden bei der Übertragung weggelassen. Mehrere Projektnummern werden mit Semikolon abgesetzt.<br />Beispiel: 311794<br />Freitextfeld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="fundingProgram').webuiPopover({
	    title:'Förderprogramm',
	    content:'Angabe des Programms innerhalb dessen die Finanzierung gegeben wurde. Einleitende Worte wie „Grant“, "Program" o.ä. werden bei der Übertragung weggelassen, es sei denn sie sind Teil eines Eigennamens. Mehrere Projektnummern werden mit Semikolon abgesetzt.<br />Beispiel: German Climate Modeling Initiative<br />Freitextfeld | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="dataOrigin').webuiPopover({
	    title:'Erhebungsform',
	    content:'Angabe wie die Daten gewonnen wurden.<br />Statische Vorschlagsliste | Mehrfachnennung möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="recordingPeriod').webuiPopover({
	    title:'Erhebungszeitraum',
	    content:'Angabe des Zeitraums, in dem die Daten erfasst wurden. Dieses kann ein Tag, aber auch mehrere Wochen oder Monate sein.<br />Vergrößerbares Freitextfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="recordingLocation').webuiPopover({
	    title:'Erhebungsort',
	    content:'Angabe des Ortes (Ortsname), an dem die Daten entstanden sind. Es findet eine Verknüpfung mit der Open Street Map statt<br />Beispiel: Sydney (AUS)<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="recordingCoordinates').webuiPopover({
	    title:'Geokoordinaten',
	    content:'Angabe der Koordinaten zum Ort an dem die Daten entstanden sind. Es findet eine Verknüpfung mit der Open Street Map statt.<br />Beispiel: -33.933,18.8641<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="reference').webuiPopover({
	    title:'Quellenangabe',
	    content:'Angabe der Publikationen, die zur Erstellung der Daten herangezogen bzw. in irgendeiner Weise zitiert wurden.<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="associatedPublication').webuiPopover({
	    title:'Zugehörige Textpublikation',
	    content:'Angabe der Artikel, Kapitel, etc. welche mit den Daten verbunden sind.<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="associatedDataset').webuiPopover({
	    title:'Zugehöriges Dataset',
	    content:'Angabe der Datensätze, die im selben Projekt entstanden sind, bzw. in irgendeiner anderen Weise mit den hier zu veröffentlichenden Daten in Verbindung stehen.<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="nextVersion').webuiPopover({
	    title:'Nachfolger Version',
	    content:'Angabe der Links zu aktuelleren Versionen der vorliegenden Daten.<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="previousVersion').webuiPopover({
	    title:'Vorgänger Version',
	    content:'Angabe der Links zu früheren Versionen der vorliegenden Daten<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="urn').webuiPopover({
	    title:'URN',
	    content:'Angabe der Uniform Rescource Number der zu veröffentlichenden Daten, wenn eine Solche bereits vergeben wurde. Gilt nur für Zweitveröffentlichungen.<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="doi').webuiPopover({
	    title:'DOI',
	    content:'Angabe des Digital Object Identifiers, falls bereits eine DOI vorhanden ist. Ist nur bei einer Zweitveröffentlichung im Fachrepositorium anzugeben. Ansonsten wird automatisch ein DOI vergeben.<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="isLike').webuiPopover({
	    title:'ähnlich zu',
	    content:'Angabe der dauerhaften URL unter der die Daten an einem anderen Ort außerhalb des FRL abrufbar sind. Gilt nur für Zweitveröffentlichungen.<br />Link-Feld | Mehrfachnennungen möglich',
	    trigger: 'hover',
	    width:'400px'
	});
	
	$('button[name="additionalNotes').webuiPopover({
	    title:'Hinweis',
	    content:'In dieses Feld können z.B. Erklärungen eingefügt werden, warum ein Volltext nachträglich gesperrt wurde o.ä. Der Inhalt des Feldes ist nach der Freigabe im Katalog sichtbar. Es handelt sich nicht um ein rein internes Feld.<br />Vergrößerbares Freitextfeld',
	    trigger: 'hover',
	    width:'400px'
	});
	
	
}
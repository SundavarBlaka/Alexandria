package admin.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import admin.controller.Controller;
import admin.model.Commento;
import admin.model.Profilo;
import admin.model.Risorsa;
import admin.model.Vendita;

public class Main {

	public static void main(String[] args) {
		view("AleXandria amministrazione");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean cont = true;
		String line = null;
		Controller c = new Controller("log.txt");
		try {
			while (cont) {
				prompt("\nadmin> ");
				if ((line = in.readLine()) == null) {
					cont = false;
				} else {
					String[] v = line.split(" ");

					// conta i token
					if (v.length < 1) {
						cont = false;
					} else {
						String command = v[0];

						switch (command) {
						case "help":
						case "h": {
							// help

							view("Comandi:");
							view("l: visualizza log");
							view("r: ottieni le informazioni riguardanti le risorse");
							view("p: ottieni le informazioni riguardanti i profili");
							view("c: ottieni le informazioni riguardanti i commenti");
							view("v: ottieni le informazioni riguardanti le vendite");
							break;
						}
						case "l": {
							// visualizza log
							prompt("Inserisci una chiave di ricerca, vuoto per vedere tutti i log: ");
							String key = in.readLine();
							List<String> log = c.getLogs(key);

							for (String str : log) {
								view(str);
							}

							break;
						}
						case "r": {
							view("1 - Cerca risorse associate ad un utente");
							view("2 - Cerca risorse in base al titolo");
							view("3 - Cerca risorse in base all'identificativo");
							view("4 - Rimuovi");

							if ((line = in.readLine()) != null) {
								switch (line) {
								case "1": {
									// cerca le risorse di un utente
									prompt("Inserisci lo username: ");
									if ((line = in.readLine()) != null) {
										for (Risorsa ris : c.getRisorsaByUsername(line)) {
											view(ris.toString());
										}
									}
									break;
								}
								case "2": {
									// cerca le risorse per titolo
									prompt("Inserisci il titolo: ");
									if ((line = in.readLine()) != null) {
										for (Risorsa ris : c.getRisorsaByTitolo(line)) {
											view(ris.toString());
										}
									}
									break;
								}
								case "3": {
									// cerca le risorse per id
									prompt("Inserisci l'identificativo: ");
									if ((line = in.readLine()) != null) {
										for (Risorsa ris : c.getRisorsaById(line)) {
											view(ris.toString());
										}
									}
									break;
								}
								case "4": {
									// cerca le risorse per id
									prompt("Inserisci l'identificativo: ");
									if ((line = in.readLine()) != null) {
										if (c.removeRisorsaById(line)) {
											view("Rimozione effettuata con successo");
										}

									}
									break;
								}
								default:
									view("Operazione non valida");
								}
							}

							break;
						}
						case "p": {
							view("1 - Cerca profilo associato ad uno username");
							view("2 - Rimuovi profilo");

							if ((line = in.readLine()) != null) {
								switch (line) {
								case "1": {
									// cerca le risorse di un utente
									prompt("Inserisci lo username: ");
									if ((line = in.readLine()) != null) {
										Profilo ris = c.getProfiloByUsername(line);
										if (ris != null) {
											view(ris.toString());
										}
									}
									break;
								}
								case "2": {
									// cerca le risorse per id
									prompt("Inserisci lo username: ");
									if ((line = in.readLine()) != null) {
										if (c.removeProfiloByUsername(line)) {
											view("Rimozione effettuata con successo");
										}

									}
									break;
								}
								default:
									view("Operazione non valida");
								}
							}

							break;
						}
						case "c": {
							view("1 - Cerca commenti associati ad una risorsa");
							view("2 - Cerca commenti associati ad un profilo");
							view("3 - Rimuovi commento");

							if ((line = in.readLine()) != null) {
								switch (line) {
								case "1": {
									// cerca le risorse di un utente
									prompt("Inserisci l'identificativo: ");
									if ((line = in.readLine()) != null) {
										List<Commento> ris = c.getCommentoByRisorsa(line);
										if (ris != null) {
											for (Commento com : ris)
												view(com.toString());
										}
									}
									break;
								}
								case "2": {
									// cerca le risorse per id
									prompt("Inserisci lo username: ");
									if ((line = in.readLine()) != null) {
										List<Commento> ris = c.getCommentoByUsername(line);
										if (ris != null) {
											for (Commento com : ris)
												view(com.toString());
										}
									}
									break;
								}
								case "3": {
									// cerca le risorse per id
									prompt("Inserisci l'identificativo: ");
									if ((line = in.readLine()) != null) {
										if (c.removeCommentoById(line)) {
											view("Rimozione effettuata con successo");
										}

									}
									break;
								}
								default:
									view("Operazione non valida");
								}
							}

							break;
						}
						case "v": {
							prompt("Inserisci acquirente, vuoto per non specificare: ");
							String acq = in.readLine();

							if (acq == null) {
								break;
							}

							prompt("Inserisci vednitore, vuoto per non specificare: ");
							String vend = in.readLine();

							if (vend == null) {
								break;
							}

							prompt("Inserisci l'identificativo della risorsa, vuoto per specificare: ");
							String ris = in.readLine();

							if (ris == null) {
								break;
							}

							for (Vendita vendita : c.getVendita(acq, vend, ris)) {
								if (vendita != null) {
									view(vendita.toString());
								}
							}
						}
						default: {

						}
						}
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void view(String out) {
		System.out.println(out);
	}

	private static void prompt(String out) {
		System.out.print(out);
	}

}

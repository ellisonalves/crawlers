package br.com.ellisonalves.crawlers;

import br.com.ellisonalves.crawlers.application.crawlers.Crawlable;
import br.com.ellisonalves.crawlers.application.crawlers.CrawlableFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ellison
 */
public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Este programa vai fazer um crawler a partir de uma fonte de dados que for especificada");
        System.out.println("Escolha uma das opçoes abaixo para fazer o crawler");
        System.out.println("wp - WebPages");
        System.out.println("fs - FileSystem");
        System.out.println("twt - Twitter");
        System.out.println("gmail - Gmail");
        System.out.println("rss - RSS");

        System.out.print("Escolha sua opção:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opcaoSelecionada = br.readLine();

        Map<String, Object> map = new HashMap<>();

        switch (opcaoSelecionada) {
            case "rss":
                // Feed de exemplo pra não ter q digitar toda hora.
                map.put(Crawlable.URL_PARAM, "http://jovemnerd.com.br/categoria/nerdcast/feed/");
                break;
            case "wp":
                map.put(Crawlable.URL_PARAM, "http://www.globo.com");
                map.put(Crawlable.PROFUNDIDADE_EXPLORACAO_PARAM, 1);
                break;
            case "fs":
                map.put(Crawlable.PATH_INICIAL_PARAM, "C:\\Users\\ellison\\Desktop");
                map.put(Crawlable.PROFUNDIDADE_EXPLORACAO_PARAM, 2);
                break;
            case "twt":
                map.put(Crawlable.TEXTO_BUSCA_PARAM, "#PokemonGO");
                break;
        }

        CrawlableFactory
                .getCrawlableInstanceFor(opcaoSelecionada)
                .crawl(map);

    }

}

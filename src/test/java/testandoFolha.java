import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class testandoFolha {

    WebDriver webdriver;

    @BeforeAll
    public void inicializandoEConfigurandoTudo() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\sandr\\Desktop\\FolhaPeTest\\src\\test\\resources\\chromedriver- windows\\chromedriver.exe");
    }

    @BeforeEach
    public void setup() {
        webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
    }

    @AfterEach
    public void closeDriver(){
        webdriver.close();
    }

    // abrindo site do folhaPe
    @Test
    public void abrindoSite() {
        webdriver.get("https://www.folhape.com.br/");
        Assertions.assertEquals("https://www.folhape.com.br/",
                webdriver.getCurrentUrl());
    }

    //selecionando categoria especifica para mostrar resultados
    @Test
    public void entrandoEmCategoria() {
        webdriver.get("https://www.folhape.com.br/");
        WebElement btCategoria = webdriver.findElement(By.xpath("/html/body/header/div[1]/div[5]/div[3]/ul/li[2]/a"));
        btCategoria.click();
        Assertions.assertEquals("https://www.folhape.com.br/economia/",
                webdriver.getCurrentUrl());
    }

    //Selecionando botão de busca para busca especifica
    @Test
    public void procurandoInfo() {
        webdriver.get("https://www.folhape.com.br/");
        WebElement btPesquisa = webdriver.findElement(By.className("buscarMascara"));
        btPesquisa.click();
        WebElement btEscrever = webdriver.findElement((By.className("inputText")));
        btEscrever.sendKeys("politica");
        btEscrever.submit();
        String resultadoPesquisa = "https://www.folhape.com.br/#gsc.tab=0&gsc.q=politica&gsc.page=1";
        Assertions.assertEquals(resultadoPesquisa,
                webdriver.getCurrentUrl());
    }

    // seleciona determinada opção de uma lista
    @Test
    public void passandoCursorEmOpcoes() {
        webdriver.get("https://www.folhape.com.br/");
        Actions acao = new Actions(webdriver);
        WebElement botao = webdriver.findElement(
                By.className("r7-menu__link"));
        acao.moveToElement(botao).perform();
        Assertions.assertTrue(botao.isEnabled());
    }

    @Test
    public void encaminhandoParaPortalR7(){
        webdriver.get("https://www.folhape.com.br/");
        WebElement botao = webdriver.findElement(
                By.id("r7-header"));
        botao.click();
        String R7 = "https://noticias.r7.com/internacional/russia-x-ucrania";
        Assertions.assertFalse(!webdriver.getCurrentUrl().contains(R7));

    }
    @Test
    public void retornandoParaTelaInicialAoClicarEmIcon(){
        webdriver.get("https://www.folhape.com.br/noticias/coronavirus/");
        WebElement clicarHome = webdriver.findElement(By.xpath("/html/body/header/div[1]/div[3]/div[2]/h1/a"));
        clicarHome.click();
        Assertions.assertFalse(!webdriver.getCurrentUrl().contains("https://www.folhape.com.br/"));
    }
    @Test
    public void selecionarItemEmBarraSelect(){
        webdriver.get("https://www.folhape.com.br/atendimento/?id_assunto_inicial=1/");
        WebElement btSelecionar = webdriver.findElement(By.xpath("//*[@id=\"ds_formAtendimento\"]/section/fieldset/div/select"));
        Select select = new Select(btSelecionar);
        select.selectByIndex(2);
        String text = "Anuncie Conosco";
        Assertions.assertTrue(select.getFirstSelectedOption().getText().contains("Anuncie Conosco"));


    }
    @Test
    public void verificandoAQuantidadeDeElementosPelaTagName(){
        webdriver.get("https://www.folhape.com.br/");
        WebElement id = webdriver.findElement(By.className("scrollMenu"));
        List<WebElement> itens = id.findElements(By.tagName("li"));
        System.out.println(itens.size());
        Assertions.assertFalse(itens.size() != 7);
    }
    @Test
    public void clicandoNoBotaodeProximaPagina(){
        webdriver.get("https://www.folhape.com.br/colunistas/folha-pet");
        WebElement bt = webdriver.findElement(By.className("arrowRight"));
        bt.click();
        Assertions.assertTrue(webdriver.getCurrentUrl().contains("https://www.folhape.com.br/colunistas/folha-pet/p/2/"));
    }
    @Test
    public void selecionandoNoticiaERedroduzindoAudio(){
        webdriver.get("https://www.folhape.com.br/");
        WebElement btPesquisa = webdriver.findElement(By.xpath("/html/body/div[6]/div[3]/section/ul/li[2]/a"));
        btPesquisa.click();
        WebElement btAudio = webdriver.findElement((By.id("audiomebtn-play")));
        btAudio.click();
        Assertions.assertTrue(btAudio.isEnabled());
    }
}


### cpzedit

---

* naming - nazvy tried piseme *CamelCase*
* organizacia - balik `models.entity` by som nahradil len ako `models`
* registracia - dovoli usrom sa registrovat s prazdnym nickname ale nasledne sa s nim nemozu prihlasit
* `UserRepo.java`
  * `findUserById` netreba, `CrudRepository` uz obsahuje `findById`
  * `findUserByNickname` sa da napisat aj ako `findByNickname`
* `Post.java`, `User.java` - *@Column* anotacia sa uvazuje automaticky a preto sa nemusi pisat
* `SessionService.java`
  * pozor na *@Getter/@Setter* pri servisoch, castokrat je neziaduce aby ktokolvek mohol pristupit
    k vlastnostiam svojvolne a menit ich
  * dal by som velky pozor na zistenie ci *User* je logged na zaklade *nickname*. Ak potrebujes ma nastaveneho pouzivatela
    tak by som zvolil identity check:
    ```java
    public class SessionService {
  
        private static User ANONYMOUS_USER = new User();
  
        private User loggedUser;
  
        // konstruktor
        public SessionService (...) {
            ...
            loggedUser = ANONYMOUS_USER;
        }
  
        public boolean isLogged() {
            return loggedUser != ANONYMOUS_USER;
        }
    }
    ```
    Pri tomto sposobe sa kontroluje identita instancie - nie je mozne sa nejako pomylit...
* `mainController.java` - `"".equals(sessionSVC.getLoggedUser().getNickname())` -> `sessionSVC.getLoggedUser().getNickname().isEmpty()`
* `registerController.java` - `postRegistration` - pouzitie *flash atributov* tu nie je potrebne (navyse ukladat heslo inde
  ako do DB nie je OK)
* `UserDTO.java` - pozor na naming `ID` -> `id`
* `homePage.html` - 
  * ```html
    <a th:if="${loginStatus==0}" th:href="'/register'"> <button type="button" class="btn btn-secondary btn-sm">Register</button> </a>
    <a th:if="${loginStatus==0}" th:href="'/login'"> <button type="button" class="btn btn-secondary btn-sm">Login</button> </a>
    ```
    Tento check vies zabalit ako
    ```html
    <div th:if="${loginStatus==0}">
        <a th:href="'/register'"> <button type="button" class="btn btn-secondary btn-sm">Register</button> </a>
        <a th:href="'/login'"> <button type="button" class="btn btn-secondary btn-sm">Login</button> </a>
    </div>
    ```
  * Pozor na *\<button\>* zabaleny v *\<a\>*: `<a th:href="'/register'"> <button type="button" class="btn btn-secondary btn-sm">Register</button> </a>`.
    Bootstrap ti dovoli urobit **\<a\>**, ktory bude vyzerat ako button
  * Ranking chyba? `<td> <a><button class="btnUp"> </button></a> <div class="rankNum" th:text="*{rating}"></div> <a><button class="btnDown"> </button> </a></td>`
  
* Opakovany kod - tato cast je nieco, co dokazeme vyriesit zo `SessionService`:
  ```java
  if (sessionSVC.getLoggedUser() == null || "".equals(sessionSVC.getLoggedUser().getNickname())) {
      sessionSVC.setLoggedUser(sessionSVC.setDummyUser());
      model.addAttribute("loggedUser", sessionSVC.getLoggedUser());
      model.addAttribute("loginStatus",0);
      model.addAttribute("modStatus", 0);
  } else {
      model.addAttribute("loggedUser", sessionSVC.getLoggedUser());
      model.addAttribute("loginStatus",1);
      model.addAttribute("modStatus", 0);
  }
  ```
* `loginController.java` - opakovany kod `findByNickname`, chcelo by to si ulozit toho usera z DB
  ```java
  if (userSVC.findByNickname(login) == null) {
      redir.addFlashAttribute("errorStatus", 1);
      return "redirect:login";
  }
  
  if (userSVC.passChecker(password, userSVC.findByNickname(login).getPassword())) {
      sessionSVC.setLoggedUser(userSVC.findByNickname(login));
  ```
* `loginPage.html` - toto je super napoveda ak by som chcel robit nejake prihlasovacie utoky :)
  ```html
  <p class="errorStatus" th:if="${errorStatus==1}" th:text="'User Not Exist!'"></p>
  <p class="errorStatus"  th:if="${errorStatus==2}" th:text="'Invalid Password!'"></p>
  ```
* `UserService.java`
  * `registerUser`: hesla v DB ako plaintext...
  * `assignPost`: `userRepo.save(user);` ukladat usera mi pride zbytocne, nemalo by to mat
    ziaden efekt
  * `passChecker`: bez pouzitia `BCryptPasswordEncoder` straca tato funkcia zmysel, to uz mozes priamo vyberat z
    DB usera podla mena a hesla (nasledne netreba kontrolovat heslo)
* `apiController.java` - `getUserByID`: treba osetrit ci user s danym *id* existuje
* `PostService.java` - `createPost`: pre usera sa inkrementuje jeho pocet postov ale ten user na neulozi do DB

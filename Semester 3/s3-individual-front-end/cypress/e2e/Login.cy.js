describe("Login page", () => {

  it("should login succesfully", () => {
    const login = {
      email: "sam@gmail.com",
      password: "sam123",
    }

    cy.intercept({
      method: "POST",
      url: "http://localhost:8080/login",
    }).as("tryLogin");
  
    cy.visit("http://localhost:3000/login");

    cy.get("#email").type(login.email);
    cy.get("#password").type(login.password);

    cy.get('[data-cy="submit"]').click({ multiple: true, force: true });

    cy.wait("@tryLogin").then((interception) => {
      expect(JSON.stringify(interception.request.body)).equal(
        JSON.stringify(login)
      )

      expect(JSON.stringify(interception.response.statusCode)).equal(
        JSON.stringify(201)
      )

      cy.url().should("match", /\/profile$/);
      cy.getAllSessionStorage('claims').should('exist')
    })
  })


  it("should not login due to invalid credentials", () => {
    const login = {
        email: "samuel@gmail.com",
        password: "sam123",
      }
  
      cy.intercept({
        method: "POST",
        url: "http://localhost:8080/login",
      }).as("tryLogin");
    
      cy.visit("http://localhost:3000/login");
  
      cy.get("#email").type(login.email);
      cy.get("#password").type(login.password);

      cy.get('[data-cy="submit"]').click({ multiple: true, force: true });

      cy.wait("@tryLogin").then((interception) => {
        expect(JSON.stringify(interception.response.statusCode)).equal(
          JSON.stringify(400)
        )
  
        cy.url().should("match", /\/login$/);
      })
  })
})
describe("Register page", () => {
  it("should register a user", () => {
    const registration = {
      username: "samuel",
      email: "sam@gmail.com",
      password: "sam123",
    };

    cy.intercept({
      method: "POST",
      url: "http://localhost:8080/users",
    }).as("tryRegistration");

    cy.visit("http://localhost:3000/register");

    cy.get("#username").type(registration.username);
    cy.get("#email").type(registration.email);
    cy.get("#password").type(registration.password);

    cy.get('[data-cy="submit"]').click({ multiple: true, force: true });

    cy.wait("@tryRegistration").then((interception) => {
      expect(JSON.stringify(interception.request.body)).equal(
        JSON.stringify(registration)
      );

      expect(JSON.stringify(interception.response.statusCode)).equal(
        JSON.stringify(201)
      )

      cy.url().should("match", /\/login$/);
    })
  });

  it("should not register a user because username already exists", () => {
    const registration = {
      username: "fabienne",
      email: "fabienne@gmail.com",
      password: "fabienne123",
    };

    cy.intercept({
      method: "POST",
      url: "http://localhost:8080/users",
    }).as("tryRegistration");

    cy.visit("http://localhost:3000/register");

    cy.get("#username").type(registration.username);
    cy.get("#email").type(registration.email);
    cy.get("#password").type(registration.password);

    cy.get('[data-cy="submit"]').click({ multiple: true, force: true });

    cy.wait("@tryRegistration").its('response.statusCode').should('be.oneOf', [400])
  });

});


import org.moqui.context.ExecutionContext

ExecutionContext ec = context.ec


if (!partyId) {
    ec.message.addError("partyId is required")
}
if (!firstName) {
    ec.message.addError("firstName is required")
}
if (!lastName) {
    ec.message.addError("lastName is required")
}

if (ec.message.hasError()) {
    return
}


def party = ec.entity.find("party.Party")
        .condition("partyId", partyId)
        .one()

if (!party) {
    ec.message.addError("No Party found for partyId: ${partyId}")
    return
}


Map personFields = [
        partyId  : partyId,
        firstName: firstName,
        lastName : lastName
]


ec.entity.makeValue("party.Person")
        .setAll(personFields)
        .create()

responseMessage = "Person created successfully for Party ID: ${partyId}"

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.ext.xerces.impl.dv;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

/**
 * Base class for datatype exceptions. For DTD types, the exception can be
 * created from an error message. For Schema types, it needs an error code
 * (as defined in Appendix C of the structure spec), plus an array of arguments,
 * for error message substitution.
 *
 * {@literal @xerces.internal}
 *
 * @author Sandy Gao, IBM
 *
 * @version $Id: DatatypeException.java 809242 2009-08-30 03:34:31Z mrglavas $
 */
@SuppressWarnings("all")
public class DatatypeException extends Exception {

    /** Serialization version. */
    static final long serialVersionUID = 1940805832730465578L;

    // used to store error code and error substitution arguments
    protected final String key;
    protected final Object[] args;

    /**
     * Create a new datatype exception by providing an error code and a list
     * of error message substitution arguments.
     *
     * @param key  error code
     * @param args error arguments
     */
    public DatatypeException(String key, Object[] args) {
        super(key);
        this.key = key;
        this.args = args;
    }

    /**
     * Return the error code
     *
     * @return  error code
     */
    public String getKey() {
        return key;
    }

    /**
     * Return the list of error arguments
     *
     * @return  error arguments
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * Overrides this method to get the formatted and localized error message.
     *
     * REVISIT: the system locale is used to load the property file.
     *          do we want to allow the application to specify a
     *          different locale?
     */
    public String getMessage() {
        return messages.getOrDefault(key, key);
    }

    private static Map<String, String> messages = messagesMap();

    private static Map<String, String> messagesMap() {
        Map<String,String> map = new HashMap<>();
        map.put("BadMessageKey ", " The error message corresponding to the message key can not be found.");
        map.put("FormatFailed ", " An internal error occurred while formatting the following message:\n");
        map.put("Internal-Error ", " Internal error: {0}.");
        map.put("dt-whitespace ", " Whitespace facet value is not available for the union simpleType ''{0}''");
        map.put("GrammarConflict ", " One of the grammar(s) returned from the user's grammar pool is in conflict with another grammar.");
        map.put("AbsentKeyValue ", " cvc-identity-constraint.4.2.1.a: Element \"{0}\" has no value for the key \"{1}\".");
        map.put("DuplicateField ", " Duplicate match in scope for field \"{0}\".");
        map.put("DuplicateKey ", " cvc-identity-constraint.4.2.2: Duplicate key value [{0}] declared for identity constraint \"{2}\" of element \"{1}\".");
        map.put("DuplicateUnique ", " cvc-identity-constraint.4.1: Duplicate unique value [{0}] declared for identity constraint \"{2}\" of element \"{1}\".");
        map.put("FieldMultipleMatch ", " cvc-identity-constraint.3: Field \"{0}\" of identity constraint \"{1}\" matches more than one value within the scope of its selector; fields must match unique values.");
        map.put("FixedDiffersFromActual ", " The content of this element is not equivalent to the value of the \"fixed\" attribute in the element's declaration in the schema.");
        map.put("KeyMatchesNillable ", " cvc-identity-constraint.4.2.3: Element \"{0}\" has the key \"{1}\" which matches an element which has nillable set to true.");
        map.put("KeyNotEnoughValues ", " cvc-identity-constraint.4.2.1.b: Not enough values specified for <key name=\"{1}\"> identity constraint specified for element \"{0}\".");
        map.put("KeyNotFound ", " cvc-identity-constraint.4.3: Key ''{0}'' with value ''{1}'' not found for identity constraint of element ''{2}''.");
        map.put("KeyRefOutOfScope ", " Identity Constraint error:  identity constraint \"{0}\" has a keyref which refers to a key or unique that is out of scope.");
        map.put("KeyRefReferNotFound ", " Key reference declaration \"{0}\" refers to unknown key with name \"{1}\".");
        map.put("UnknownField ", " Internal identity constraint error; unknown field \"{0}\" for identity constraint \"{2}\" specified for element \"{1}\".");
        map.put("cvc-attribute.3 ", " cvc-attribute.3: The value ''{2}'' of attribute ''{1}'' on element ''{0}'' is not valid with respect to its type, ''{3}''.");
        map.put("cvc-attribute.4 ", " cvc-attribute.4: The value ''{2}'' of attribute ''{1}'' on element ''{0}'' is not valid with respect to its fixed '{'value constraint'}'. The attribute must have a value of ''{3}''.");
        map.put("cvc-complex-type.2.1 ", " cvc-complex-type.2.1: Element ''{0}'' must have no character or element information item [children], because the type''s content type is empty.");
        map.put("cvc-complex-type.2.2 ", " cvc-complex-type.2.2: Element ''{0}'' must have no element [children], and the value must be valid.");
        map.put("cvc-complex-type.2.3 ", " cvc-complex-type.2.3: Element ''{0}'' cannot have character [children], because the type''s content type is element-only.");
        map.put("cvc-complex-type.2.4.a ", " cvc-complex-type.2.4.a: Invalid content was found starting with element ''{0}''. One of ''{1}'' is expected.");
        map.put("cvc-complex-type.2.4.b ", " cvc-complex-type.2.4.b: The content of element ''{0}'' is not complete. One of ''{1}'' is expected.");
        map.put("cvc-complex-type.2.4.c ", " cvc-complex-type.2.4.c: The matching wildcard is strict, but no declaration can be found for element ''{0}''.");
        map.put("cvc-complex-type.2.4.d ", " cvc-complex-type.2.4.d: Invalid content was found starting with element ''{0}''. No child element is expected at this point.");
        map.put("cvc-complex-type.2.4.e ", " cvc-complex-type.2.4.e: ''{0}'' can occur a maximum of ''{2}'' times in the current sequence. This limit was exceeded. At this point one of ''{1}'' is expected.");
        map.put("cvc-complex-type.2.4.f ", " cvc-complex-type.2.4.f: ''{0}'' can occur a maximum of ''{1}'' times in the current sequence. This limit was exceeded. No child element is expected at this point.");
        map.put("cvc-complex-type.2.4.g ", " cvc-complex-type.2.4.g: Invalid content was found starting with element ''{0}''. ''{1}'' is expected to occur a minimum of ''{2}'' times in the current sequence. One more instance is required to satisfy this constraint.");
        map.put("cvc-complex-type.2.4.h ", " cvc-complex-type.2.4.h: Invalid content was found starting with element ''{0}''. ''{1}'' is expected to occur a minimum of ''{2}'' times in the current sequence. ''{3}'' more instances are required to satisfy this constraint.");
        map.put("cvc-complex-type.2.4.i ", " cvc-complex-type.2.4.i: The content of element ''{0}'' is not complete. ''{1}'' is expected to occur a minimum of ''{2}'' times. One more instance is required to satisfy this constraint.");
        map.put("cvc-complex-type.2.4.j ", " cvc-complex-type.2.4.j: The content of element ''{0}'' is not complete. ''{1}'' is expected to occur a minimum of ''{2}'' times. ''{3}'' more instances are required to satisfy this constraint.");
        map.put("cvc-complex-type.3.1 ", " cvc-complex-type.3.1: Value ''{2}'' of attribute ''{1}'' of element ''{0}'' is not valid with respect to the corresponding attribute use. Attribute ''{1}'' has a fixed value of ''{3}''.");
        map.put("cvc-complex-type.3.2.1 ", " cvc-complex-type.3.2.1: Element ''{0}'' does not have an attribute wildcard for attribute ''{1}''.");
        map.put("cvc-complex-type.3.2.2 ", " cvc-complex-type.3.2.2: Attribute ''{1}'' is not allowed to appear in element ''{0}''.");
        map.put("cvc-complex-type.4 ", " cvc-complex-type.4: Attribute ''{1}'' must appear on element ''{0}''.");
        map.put("cvc-complex-type.5.1 ", " cvc-complex-type.5.1: In element ''{0}'', attribute ''{1}'' is a Wild ID. But there is already a Wild ID ''{2}''. There can be only one.");
        map.put("cvc-complex-type.5.2 ", " cvc-complex-type.5.2: In element ''{0}'', attribute ''{1}'' is a Wild ID. But there is already an attribute ''{2}'' derived from ID among the '{'attribute uses'}'.");
        map.put("cvc-datatype-valid.1.2.1 ", " cvc-datatype-valid.1.2.1: ''{0}'' is not a valid value for ''{1}''.");
        map.put("cvc-datatype-valid.1.2.2 ", " cvc-datatype-valid.1.2.2: ''{0}'' is not a valid value of list type ''{1}''.");
        map.put("cvc-datatype-valid.1.2.3 ", " cvc-datatype-valid.1.2.3: ''{0}'' is not a valid value of union type ''{1}''.");
        map.put("cvc-elt.1.a ", " cvc-elt.1.a: Cannot find the declaration of element ''{0}''.");
        map.put("cvc-elt.1.b ", " cvc-elt.1.b: The name of the element does not match the name of the element declaration. Saw ''{0}''. Expected ''{1}''.");
        map.put("cvc-elt.2 ", " cvc-elt.2: The value of '{'abstract'}' in the element declaration for ''{0}'' must be false.");
        map.put("cvc-elt.3.1 ", " cvc-elt.3.1: Attribute ''{1}'' must not appear on element ''{0}'', because the '{'nillable'}' property of ''{0}'' is false.");
        map.put("cvc-elt.3.2.1 ", " cvc-elt.3.2.1: Element ''{0}'' cannot have character or element information [children], because ''{1}'' is specified.");
        map.put("cvc-elt.3.2.2 ", " cvc-elt.3.2.2: There must be no fixed '{'value constraint'}' for element ''{0}'', because ''{1}'' is specified.");
        map.put("cvc-elt.4.1 ", " cvc-elt.4.1: The value ''{2}'' of attribute ''{1}'' of element ''{0}'' is not a valid QName.");
        map.put("cvc-elt.4.2 ", " cvc-elt.4.2: Cannot resolve ''{1}'' to a type definition for element ''{0}''.");
        map.put("cvc-elt.4.3 ", " cvc-elt.4.3: Type ''{1}'' is not validly derived from the type definition, ''{2}'', of element ''{0}''.");
        map.put("cvc-elt.5.1.1 ", " cvc-elt.5.1.1: '{'value constraint'}' ''{2}'' of element ''{0}'' is not a valid default value for type ''{1}''.");
        map.put("cvc-elt.5.2.2.1 ", " cvc-elt.5.2.2.1: Element ''{0}'' must have no element information item [children].");
        map.put("cvc-elt.5.2.2.2.1 ", " cvc-elt.5.2.2.2.1: The value ''{1}'' of element ''{0}'' does not match the fixed '{'value constraint'}' value ''{2}''.");
        map.put("cvc-elt.5.2.2.2.2 ", " cvc-elt.5.2.2.2.2: The value ''{1}'' of element ''{0}'' does not match the '{'value constraint'}' value ''{2}''.");
        map.put("cvc-enumeration-valid ", " cvc-enumeration-valid: Value ''{0}'' is not facet-valid with respect to enumeration ''{1}''. It must be a value from the enumeration.");
        map.put("cvc-fractionDigits-valid ", " cvc-fractionDigits-valid: Value ''{0}'' has {1} fraction digits, but the number of fraction digits has been limited to {2}.");
        map.put("cvc-id.1 ", " cvc-id.1: There is no ID/IDREF binding for IDREF ''{0}''.");
        map.put("cvc-id.2 ", " cvc-id.2: There are multiple occurrences of ID value ''{0}''.");
        map.put("cvc-id.3 ", " cvc-id.3: A field of identity constraint ''{0}'' matched element ''{1}'', but this element does not have a simple type.");
        map.put("cvc-length-valid ", " cvc-length-valid: Value ''{0}'' with length = ''{1}'' is not facet-valid with respect to length ''{2}'' for type ''{3}''.");
        map.put("cvc-maxExclusive-valid ", " cvc-maxExclusive-valid: Value ''{0}'' is not facet-valid with respect to maxExclusive ''{1}'' for type ''{2}''.");
        map.put("cvc-maxInclusive-valid ", " cvc-maxInclusive-valid: Value ''{0}'' is not facet-valid with respect to maxInclusive ''{1}'' for type ''{2}''.");
        map.put("cvc-maxLength-valid ", " cvc-maxLength-valid: Value ''{0}'' with length = ''{1}'' is not facet-valid with respect to maxLength ''{2}'' for type ''{3}''.");
        map.put("cvc-minExclusive-valid ", " cvc-minExclusive-valid: Value ''{0}'' is not facet-valid with respect to minExclusive ''{1}'' for type ''{2}''.");
        map.put("cvc-minInclusive-valid ", " cvc-minInclusive-valid: Value ''{0}'' is not facet-valid with respect to minInclusive ''{1}'' for type ''{2}''.");
        map.put("cvc-minLength-valid ", " cvc-minLength-valid: Value ''{0}'' with length = ''{1}'' is not facet-valid with respect to minLength ''{2}'' for type ''{3}''.");
        map.put("cvc-pattern-valid ", " cvc-pattern-valid: Value ''{0}'' is not facet-valid with respect to pattern ''{1}'' for type ''{2}''.");
        map.put("cvc-totalDigits-valid ", " cvc-totalDigits-valid: Value ''{0}'' has {1} total digits, but the number of total digits has been limited to {2}.");
        map.put("cvc-type.1 ", " cvc-type.1: The type definition ''{0}'' was not found.");
        map.put("cvc-type.2 ", " cvc-type.2: The type definition cannot be abstract for element {0}.");
        map.put("cvc-type.3.1.1 ", " cvc-type.3.1.1: Element ''{0}'' is a simple type, so it cannot have attributes, excepting those whose namespace name is identical to ''http://www.w3.org/2001/XMLSchema-instance'' and whose [local name] is one of ''type'', ''nil'', ''schemaLocation'' or ''noNamespaceSchemaLocation''. However, the attribute, ''{1}'' was found.");
        map.put("cvc-type.3.1.2 ", " cvc-type.3.1.2: Element ''{0}'' is a simple type, so it must have no element information item [children].");
        map.put("cvc-type.3.1.3 ", " cvc-type.3.1.3: The value ''{1}'' of element ''{0}'' is not valid.");
        map.put("schema_reference.4 ", " schema_reference.4: Failed to read schema document ''{0}'', because 1) could not find the document; 2) the document could not be read; 3) the root element of the document is not <xsd:schema>.");
        map.put("src-annotation ", " src-annotation: <annotation> elements can only contain <appinfo> and <documentation> elements, but ''{0}'' was found.");
        map.put("src-attribute.1 ", " src-attribute.1: The properties ''default'' and ''fixed'' cannot both be present in attribute declaration ''{0}''. Use only one of them.");
        map.put("src-attribute.2 ", " src-attribute.2: : The property ''default'' is present in attribute ''{0}'', so the value of ''use'' must be ''optional''.");
        map.put("src-attribute.3.1 ", " src-attribute.3.1: One of 'ref' or 'name' must be present in a local attribute declaration.");
        map.put("src-attribute.3.2 ", " src-attribute.3.2: The content must match (annotation?) for the attribute reference ''{0}''.");
        map.put("src-attribute.4 ", " src-attribute.4: Attribute ''{0}'' has both a ''type'' attribute and an anonymous ''simpleType'' child. Only one of these is allowed for an attribute.");
        map.put("src-attribute_group.2 ", " src-attribute_group.2: The intersection of wildcards is not expressible for attribute group ''{0}''.");
        map.put("src-attribute_group.3 ", " src-attribute_group.3: Circular definitions detected for attribute group ''{0}''. Recursively following attribute group references eventually leads back to itself.");
        map.put("src-ct.1 ", " src-ct.1: Complex Type Definition Representation Error for type ''{0}''.  When <complexContent> is used, the base type must be a complexType. ''{1}'' is a simpleType.");
        map.put("src-ct.2.1 ", " src-ct.2.1: Complex Type Definition Representation Error for type ''{0}''.  When <simpleContent> is used, the base type must be a complexType whose content type is simple, or, only if restriction is specified, a complex type with mixed content and emptiable particle, or, only if extension is specified, a simple type. ''{1}'' satisfies none of these conditions.");
        map.put("src-ct.2.2 ", " src-ct.2.2: Complex Type Definition Representation Error for type ''{0}''.  When a complexType with simpleContent restricts a complexType with mixed content and emptiable particle, then there must be a <simpleType> among the children of <restriction>.");
        map.put("src-ct.4 ", " src-ct.4: Complex Type Definition Representation Error for type ''{0}''. The intersection of wildcards is not expressible.");
        map.put("src-ct.5 ", " src-ct.5: Complex Type Definition Representation Error for type ''{0}''. The union of wildcards is not expressible.");
        map.put("src-element.1 ", " src-element.1: The properties ''default'' and ''fixed'' cannot both be present in element declaration ''{0}''. Use only one of them.");
        map.put("src-element.2.1 ", " src-element.2.1: : One of 'ref' or 'name' must be present in a local element declaration.");
        map.put("src-element.2.2 ", " src-element.2.2: Since ''{0}'' contains the ''ref'' attribute, its content must match (annotation?). However, ''{1}'' was found.");
        map.put("src-element.3 ", " src-element.3: Element ''{0}'' has both a ''type'' attribute and a ''anonymous type'' child. Only one of these is allowed for an element.");
        map.put("src-import.1.1 ", " src-import.1.1: The namespace attribute ''{0}'' of an <import> element information item must not be the same as the targetNamespace of the schema it exists in.");
        map.put("src-import.1.2 ", " src-import.1.2: If the namespace attribute is not present on an <import> element information item then the enclosing schema must have a targetNamespace.");
        map.put("src-import.2 ", " src-import.2: The root element of document ''{0}'' has to have the namespace name ''http://www.w3.org/2001/XMLSchema'' and the local name ''schema''.");
        map.put("src-import.3.1 ", " src-import.3.1: The namespace attribute, ''{0}'', of an <import> element information item must be identical to the targetNamespace attribute, ''{1}'', of the imported document.");
        map.put("src-import.3.2 ", " src-import.3.2: An <import> element information item that had no namespace attribute was found, so the imported document cannot have a targetNamespace attribute. However, the targetNamespace ''{1}'' was found in the imported document.");
        map.put("src-include.1 ", " src-include.1: The root element of document ''{0}'' has to have the namespace name ''http://www.w3.org/2001/XMLSchema'' and the local name ''schema''.");
        map.put("src-include.2.1 ", " src-include.2.1: The targetNamespace of the referenced schema, currently ''{1}'', must be identical to that of the including schema, currently ''{0}''.");
        map.put("src-redefine.2 ", " src-redefine.2: The root element of document ''{0}'' has to have the namespace name ''http://www.w3.org/2001/XMLSchema'' and the local name ''schema''.");
        map.put("src-redefine.3.1 ", " src-redefine.3.1: The targetNamespace of the referenced schema, currently ''{1}'', must be identical to that of the redefining schema, currently ''{0}''.");
        map.put("src-redefine.5.a.a ", " src-redefine.5.a.a: No non-annotation children of <simpleType> were found. <simpleType> children of <redefine> elements must have <restriction> descendants, with 'base' attributes that refer to themselves.");
        map.put("src-redefine.5.a.b ", " src-redefine.5.a.b: ''{0}'' is not a valid child element. <simpleType> children of <redefine> elements must have <restriction> descendants, with ''base'' attributes that refer to themselves.");
        map.put("src-redefine.5.a.c ", " src-redefine.5.a.c: ''{0}'' does not have a ''base'' attribute that refers to the redefined element, ''{1}''. <simpleType> children of <redefine> elements must have <restriction> descendants, with ''base'' attributes that refer to themselves.");
        map.put("src-redefine.5.b.a ", " src-redefine.5.b.a: No non-annotation children of <complexType> were found. <complexType> children of <redefine> elements must have <extension> or <restriction> descendants, with 'base' attributes that refer to themselves.");
        map.put("src-redefine.5.b.b ", " src-redefine.5.b.b: No non-annotation grandchildren of <complexType> were found. <complexType> children of <redefine> elements must have <extension> or <restriction> descendants, with 'base' attributes that refer to themselves.");
        map.put("src-redefine.5.b.c ", " src-redefine.5.b.c: ''{0}'' is not a valid grandchild element. <complexType> children of <redefine> elements must have <extension> or <restriction> descendants, with ''base'' attributes that refer to themselves.");
        map.put("src-redefine.5.b.d ", " src-redefine.5.b.d: ''{0}'' does not have a ''base'' attribute that refers to the redefined element, ''{1}''. <complexType> children of <redefine> elements must have <extension> or <restriction> descendants, with ''base'' attributes that refer to themselves.");
        map.put("src-redefine.6.1.1 ", " src-redefine.6.1.1:  If a group child of a <redefine> element contains a group referring itself, it must have exactly 1; this one has ''{0}''.");
        map.put("src-redefine.6.1.2 ", " src-redefine.6.1.2:  The group ''{0}'', which contains a reference to a group being redefined, must have ''minOccurs'' = ''maxOccurs'' = 1.");
        map.put("src-redefine.6.2.1 ", " src-redefine.6.2.1: No group in the redefined schema has a name matching ''{0}''.");
        map.put("src-redefine.6.2.2 ", " src-redefine.6.2.2: Group ''{0}'' does not properly restrict the group it redefines; constraint violated:  ''{1}''.");
        map.put("src-redefine.7.1 ", " src-redefine.7.1:  If an attributeGroup child of a <redefine> element contains an attributeGroup referring itself, it must have exactly 1; this one has {0}.");
        map.put("src-redefine.7.2.1 ", " src-redefine.7.2.1: No attributeGroup in the redefined schema has a name matching ''{0}''.");
        map.put("src-redefine.7.2.2 ", " src-redefine.7.2.2: AttributeGroup ''{0}'' does not properly restrict the attributeGroup it redefines; constraint violated:  ''{1}''.");
        map.put("src-resolve ", " src-resolve: Cannot resolve the name ''{0}'' to a(n) ''{1}'' component.");
        map.put("src-resolve.4.1 ", " src-resolve.4.1: Error resolving component ''{2}''. It was detected that ''{2}'' has no namespace, but components with no target namespace are not referenceable from schema document ''{0}''. If ''{2}'' is intended to have a namespace, perhaps a prefix needs to be provided. If it is intended that ''{2}'' has no namespace, then an ''import'' without a \"namespace\" attribute should be added to ''{0}''.");
        map.put("src-resolve.4.2 ", " src-resolve.4.2: Error resolving component ''{2}''. It was detected that ''{2}'' is in namespace ''{1}'', but components from this namespace are not referenceable from schema document ''{0}''. If this is the incorrect namespace, perhaps the prefix of ''{2}'' needs to be changed. If this is the correct namespace, then an appropriate ''import'' tag should be added to ''{0}''.");
        map.put("src-simple-type.2.a ", " src-simple-type.2.a: A <restriction> element was found that has both a base [attribute] and a <simpleType> element among its [children]. Only one is allowed.");
        map.put("src-simple-type.2.b ", " src-simple-type.2.b: A <restriction> element was found that has neither a base [attribute] nor a <simpleType> element among its [children]. One is required.");
        map.put("src-simple-type.3.a ", " src-simple-type.3.a: A <list> element was found that has both an itemType [attribute] and a <simpleType> element among its [children]. Only one is allowed.");
        map.put("src-simple-type.3.b ", " src-simple-type.3.b: A <list> element was found that has neither an itemType [attribute] nor a <simpleType> element among its [children]. One is required.");
        map.put("src-single-facet-value ", " src-single-facet-value: The facet ''{0}'' is defined more than once.");
        map.put("src-union-memberTypes-or-simpleTypes ", " src-union-memberTypes-or-simpleTypes: A <union> element must have either a non-empty memberTypes [attribute] or at least one <simpleType> element among its [children].");
        map.put("ag-props-correct.2 ", " ag-props-correct.2: Error for attribute group ''{0}''.  Duplicate attribute uses with the same name and target namespace are specified.  Name of duplicate attribute use is ''{1}''.");
        map.put("ag-props-correct.3 ", " ag-props-correct.3: Error for attribute group ''{0}''.  Two attribute declarations, ''{1}'' and ''{2}'' have types which are derived from ID.");
        map.put("a-props-correct.2 ", " a-props-correct.2: Invalid value constraint value ''{1}'' in attribute ''{0}''.");
        map.put("a-props-correct.3 ", " a-props-correct.3: Attribute ''{0}'' cannot use ''fixed'' or ''default'', because the attribute''s '{'type definition'}' is ID, or is derived from ID.");
        map.put("au-props-correct.2 ", " au-props-correct.2: In the attribute declaration of ''{0}'', a fixed value of ''{1}'' was specified. So if the attribute use referring to ''{0}'' also has a '{'value constraint'}', it must be fixed and its value must be ''{1}''.");
        map.put("cos-all-limited.1.2 ", " cos-all-limited.1.2: An 'all' model group must appear in a particle with '{'min occurs'}' = '{'max occurs'}' = 1, and that particle must be part of a pair which constitutes the '{'content type'}' of a complex type definition.");
        map.put("cos-all-limited.2 ", " cos-all-limited.2: The '{'max occurs'}' of an element in an ''all'' model group must be 0 or 1. The value ''{0}'' for element ''{1}'' is invalid.");
        map.put("cos-applicable-facets ", " cos-applicable-facets: Facet ''{0}'' is not allowed by type {1}.");
        map.put("cos-ct-extends.1.1 ", " cos-ct-extends.1.1: Type ''{0}'' was derived by extension from type ''{1}''.  However, the ''final'' attribute of ''{1}'' forbids derivation by extension.");
        map.put("cos-ct-extends.1.4.3.2.2.1.a ", " cos-ct-extends.1.4.3.2.2.1.a: The content type of a derived type and that of its base must both be mixed or both be element-only. Type ''{0}'' is element only, but its base type is not.");
        map.put("cos-ct-extends.1.4.3.2.2.1.b ", " cos-ct-extends.1.4.3.2.2.1.b: The content type of a derived type and that of its base must both be mixed or both be element-only. Type ''{0}'' is mixed, but its base type is not.");
        map.put("cos-element-consistent ", " cos-element-consistent: Error for type ''{0}''. Multiple elements with name ''{1}'', with different types, appear in the model group.");
        map.put("cos-list-of-atomic ", " cos-list-of-atomic: In the definition of list type ''{0}'', type ''{1}'' is an invalid list element type because it is not atomic (''{1}'' is either a list type, or a union type which contains a list).");
        map.put("cos-nonambig ", " cos-nonambig: {0} and {1} (or elements from their substitution group) violate \"Unique Particle Attribution\". During validation against this schema, ambiguity would be created for those two particles.");
        map.put("cos-particle-restrict.a ", " cos-particle-restrict.a: Derived particle is empty, and base is not emptiable.");
        map.put("cos-particle-restrict.b ", " cos-particle-restrict.b: Base particle is empty, but derived particle is not.");
        map.put("cos-particle-restrict.2 ", " cos-particle-restrict.2: Forbidden particle restriction: ''{0}''.");
        map.put("cos-st-restricts.1.1 ", " cos-st-restricts.1.1: The type ''{1}'' is atomic, so its '{'base type definition'}', ''{0}'', must be an atomic simple type definition or a built-in primitive datatype.");
        map.put("cos-st-restricts.2.1 ", " cos-st-restricts.2.1: In the definition of list type ''{0}'', type ''{1}'' is an invalid item type because it is either a list type, or a union type that contains a list.");
        map.put("cos-st-restricts.2.3.1.1 ", " cos-st-restricts.2.3.1.1: The '{'final'}' component of the '{'item type definition'}', ''{0}'', contains ''list''. This means that ''{0}'' cannot be used as an item type for list type ''{1}''.");
        map.put("cos-st-restricts.3.3.1.1 ", " cos-st-restricts.3.3.1.1: The '{'final'}' component of the '{'member type definitions'}', ''{0}'', contains ''union''. This means that ''{0}'' cannot be used as an member type for union type ''{1}''.");
        map.put("cos-valid-default.2.1 ", " cos-valid-default.2.1: Element ''{0}'' has a value constraint and must have a mixed or simple content model.");
        map.put("cos-valid-default.2.2.2 ", " cos-valid-default.2.2.2: Since element ''{0}'' has a '{'value constraint'}' and its type definition has mixed '{'content type'}', then the particle of the '{'content type'}' must be emptiable.");
        map.put("c-props-correct.2 ", " c-props-correct.2: Cardinality of Fields for keyref ''{0}'' and key ''{1}'' must match each other.");
        map.put("ct-props-correct.3 ", " ct-props-correct.3: Circular definitions detected for complex type ''{0}''. This means that ''{0}'' is contained in its own type hierarchy, which is an error.");
        map.put("ct-props-correct.4 ", " ct-props-correct.4: Error for type ''{0}''. Duplicate attribute uses with the same name and target namespace are specified.  Name of duplicate attribute use is ''{1}''.");
        map.put("ct-props-correct.5 ", " ct-props-correct.5: Error for type ''{0}''. Two attribute declarations, ''{1}'' and ''{2}'' have types which are derived from ID.");
        map.put("derivation-ok-restriction.1 ", " derivation-ok-restriction.1: Type ''{0}'' was derived by restriction from type ''{1}''.  However, ''{1}'' has a '{'final'}' property that forbids derivation by restriction.");
        map.put("derivation-ok-restriction.2.1.1 ", " derivation-ok-restriction.2.1.1: Error for type ''{0}''.  The attribute use ''{1}'' in this type has a ''use'' value of ''{2}'', which is inconsistent with the value of ''required'' in a matching attribute use in the base type.");
        map.put("derivation-ok-restriction.2.1.2 ", " derivation-ok-restriction.2.1.2: Error for type ''{0}''.  The attribute use ''{1}'' in this type has type ''{2}'', which is not validly derived from ''{3}'', the type of the matching attribute use in the base type.");
        map.put("derivation-ok-restriction.2.1.3.a ", " derivation-ok-restriction.2.1.3.a: Error for type ''{0}''.  The attribute use ''{1}'' in this type has an effective value constraint which is not fixed, and the effective value constraint of the matching attribute use in the base type is fixed.");
        map.put("derivation-ok-restriction.2.1.3.b ", " derivation-ok-restriction.2.1.3.b: Error for type ''{0}''.  The attribute use ''{1}'' in this type has an effective value constraint fixed with a value of ''{2}'', which is not consistent with the value of ''{3}'' for the fixed effective value constraint of the matching attribute use in the base type.");
        map.put("derivation-ok-restriction.2.2.a ", " derivation-ok-restriction.2.2.a: Error for type ''{0}''.  The attribute use ''{1}'' in this type does not have a matching attribute use in the base, and the base type does not have a wildcard attribute.");
        map.put("derivation-ok-restriction.2.2.b ", " derivation-ok-restriction.2.2.b: Error for type ''{0}''.  The attribute use ''{1}'' in this type does not have a matching attribute use in the base, and the wildcard in the base type does not allow the namespace ''{2}'' of this attribute use.");
        map.put("derivation-ok-restriction.3 ", " derivation-ok-restriction.3: Error for type ''{0}''.  The attribute use ''{1}'' in the base type has REQUIRED as true, but there is no matching attribute use in the derived type.");
        map.put("derivation-ok-restriction.4.1 ", " derivation-ok-restriction.4.1: Error for type ''{0}''.  The derivation has an attribute wildcard, but the base does not have one.");
        map.put("derivation-ok-restriction.4.2 ", " derivation-ok-restriction.4.2: Error for type ''{0}''.  The wildcard in the derivation is not a valid wildcard subset of the one in the base.");
        map.put("derivation-ok-restriction.4.3 ", " derivation-ok-restriction.4.3: Error for type ''{0}''.  The process contents of the wildcard in the derivation ({1}) is weaker than that in the base ({2}).");
        map.put("derivation-ok-restriction.5.2.2.1 ", " derivation-ok-restriction.5.2.2.1: Error for type ''{0}''.  The simple content type of this type, ''{1}'', is not a valid restriction of the simple content type of the base, ''{2}''.");
        map.put("derivation-ok-restriction.5.3.2 ", " derivation-ok-restriction.5.3.2: Error for type ''{0}''.  The content type of this type is empty, but the content type of the base, ''{1}'', is not empty or emptiable.");
        map.put("derivation-ok-restriction.5.4.1.2 ", " derivation-ok-restriction.5.4.1.2: Error for type ''{0}''.  The content type of this type is mixed, but the content type of the base, ''{1}'', is not.");
        map.put("derivation-ok-restriction.5.4.2 ", " derivation-ok-restriction.5.4.2: Error for type ''{0}''.  The particle of the type is not a valid restriction of the particle of the base.");
        map.put("enumeration-required-notation ", " enumeration-required-notation: The NOTATION type, ''{0}'' used by {2} ''{1}'', must have an enumeration facet value which specifies the notation elements used by this type.");
        map.put("enumeration-valid-restriction ", " enumeration-valid-restriction: Enumeration value ''{0}'' is not in the value space of the base type, {1}.");
        map.put("e-props-correct.2 ", " e-props-correct.2: Invalid value constraint value ''{1}'' in element ''{0}''.");
        map.put("e-props-correct.4 ", " e-props-correct.4: The '{'type definition'}' of element ''{0}'' is not validly derived from the '{'type definition'}' of the substitutionHead ''{1}'', or the '{'substitution group exclusions'}' property of ''{1}'' does not allow this derivation.");
        map.put("e-props-correct.5 ", " e-props-correct.5: A '{'value constraint'}' must not be present on element ''{0}'', because the element''s '{'type definition'}' or '{'type definition'}'''s '{'content type'}' is ID, or is derived from ID.");
        map.put("e-props-correct.6 ", " e-props-correct.6: Circular substitution group detected for element ''{0}''.");
        map.put("fractionDigits-valid-restriction ", " fractionDigits-valid-restriction: In the definition of {2}, the value ''{0}'' for the facet ''fractionDigits'' is invalid, because it must be <= the value for ''fractionDigits'' which was set to ''{1}'' in one of the ancestor types.");
        map.put("fractionDigits-totalDigits ", " fractionDigits-totalDigits: In the definition of {2}, the value ''{0}'' for the facet ''fractionDigits'' is invalid, because the value must be <= the value for ''totalDigits'' which is ''{1}''.");
        map.put("length-minLength-maxLength.1.1 ", " length-minLength-maxLength.1.1: For type {0}, it is an error for the value of length ''{1}'' to be less than the value of minLength ''{2}''.");
        map.put("length-minLength-maxLength.1.2.a ", " length-minLength-maxLength.1.2.a: For type {0}, it is an error for the base to not have a minLength facet if the current restriction has the minLength facet and the current restriction or base has the length facet. ");
        map.put("length-minLength-maxLength.1.2.b ", " length-minLength-maxLength.1.2.b: For type {0}, it is an error for the current minLength ''{1}'' to not equal the base minLength ''{2}''.");
        map.put("length-minLength-maxLength.2.1 ", " length-minLength-maxLength.2.1: For type {0}, it is an error for the value of length ''{1}'' to be greater than the value of maxLength ''{2}''. ");
        map.put("length-minLength-maxLength.2.2.a ", " length-minLength-maxLength.2.2.a: For type {0}, it is an error for the base to not have a maxLength facet if the current restriction has the maxLength facet and the current restriction or base has the length facet. ");
        map.put("length-minLength-maxLength.2.2.b ", " length-minLength-maxLength.2.2.b: For type {0}, it is an error for the current maxLength ''{1}'' to not equal the base maxLength ''{2}''.");
        map.put("length-valid-restriction ", " length-valid-restriction: Error for type ''{2}''. The value of length = ''{0}'' must be = the value of that of the base type ''{1}''.");
        map.put("maxExclusive-valid-restriction.1 ", " maxExclusive-valid-restriction.1: Error for type ''{2}''. The maxExclusive value =''{0}'' must be <= maxExclusive of the base type ''{1}''.");
        map.put("maxExclusive-valid-restriction.2 ", " maxExclusive-valid-restriction.2: Error for type ''{2}''. The maxExclusive value =''{0}'' must be <= maxInclusive of the base type ''{1}''.");
        map.put("maxExclusive-valid-restriction.3 ", " maxExclusive-valid-restriction.3: Error for type ''{2}''. The maxExclusive value =''{0}'' must be > minInclusive of the base type ''{1}''.");
        map.put("maxExclusive-valid-restriction.4 ", " maxExclusive-valid-restriction.4: Error for type ''{2}''. The maxExclusive value =''{0}'' must be > minExclusive of the base type ''{1}''.");
        map.put("maxInclusive-maxExclusive ", " maxInclusive-maxExclusive: It is an error for both maxInclusive and maxExclusive to be specified for the same datatype. In {2}, maxInclusive = ''{0}'' and maxExclusive = ''{1}''.");
        map.put("maxInclusive-valid-restriction.1 ", " maxInclusive-valid-restriction.1: Error for type ''{2}''. The maxInclusive value =''{0}'' must be <= maxInclusive of the base type ''{1}''.");
        map.put("maxInclusive-valid-restriction.2 ", " maxInclusive-valid-restriction.2: Error for type ''{2}''. The maxInclusive value =''{0}'' must be < maxExclusive of the base type ''{1}''.");
        map.put("maxInclusive-valid-restriction.3 ", " maxInclusive-valid-restriction.3: Error for type ''{2}''. The maxInclusive value =''{0}'' must be >= minInclusive of the base type ''{1}''.");
        map.put("maxInclusive-valid-restriction.4 ", " maxInclusive-valid-restriction.4: Error for type ''{2}''. The maxInclusive value =''{0}'' must be > minExclusive of the base type ''{1}''.");
        map.put("maxLength-valid-restriction ", " maxLength-valid-restriction: In the definition of {2}, maxLength value = ''{0}'' must be <= that of the base type ''{1}''.");
        map.put("mg-props-correct.2 ", " mg-props-correct.2: Circular definitions detected for group ''{0}''. Recursively following the '{'term'}' values of the particles leads to a particle whose '{'term'}' is the group itself.");
        map.put("minExclusive-less-than-equal-to-maxExclusive ", " minExclusive-less-than-equal-to-maxExclusive: In the definition of {2}, minExclusive value = ''{0}'' must be <= maxExclusive value = ''{1}''.");
        map.put("minExclusive-less-than-maxInclusive ", " minExclusive-less-than-maxInclusive: In the definition of {2}, minExclusive value = ''{0}'' must be < maxInclusive value = ''{1}''.");
        map.put("minExclusive-valid-restriction.1 ", " minExclusive-valid-restriction.1: Error for type ''{2}''. The minExclusive value =''{0}'' must be >= minExclusive of the base type ''{1}''.");
        map.put("minExclusive-valid-restriction.2 ", " minExclusive-valid-restriction.2: Error for type ''{2}''. The minExclusive value =''{0}'' must be <= maxInclusive of the base type ''{1}''.");
        map.put("minExclusive-valid-restriction.3 ", " minExclusive-valid-restriction.3: Error for type ''{2}''. The minExclusive value =''{0}'' must be >= minInclusive of the base type ''{1}''.");
        map.put("minExclusive-valid-restriction.4 ", " minExclusive-valid-restriction.4: Error for type ''{2}''. The minExclusive value =''{0}'' must be < maxExclusive of the base type ''{1}''.");
        map.put("minInclusive-less-than-equal-to-maxInclusive ", " minInclusive-less-than-equal-to-maxInclusive: In the definition of {2}, minInclusive value = ''{0}'' must be <= maxInclusive value = ''{1}''.");
        map.put("minInclusive-less-than-maxExclusive ", " minInclusive-less-than-maxExclusive: In the definition of {2}, minInclusive value = ''{0}'' must be < maxExclusive value = ''{1}''.");
        map.put("minInclusive-minExclusive ", " minInclusive-minExclusive: It is an error for both minInclusive and minExclusive to be specified for the same datatype. In {2}, minInclusive = ''{0}'' and minExclusive = ''{1}''.");
        map.put("minInclusive-valid-restriction.1 ", " minInclusive-valid-restriction.1: Error for type ''{2}''. The minInclusive value =''{0}'' must be >= minInclusive of the base type ''{1}''.");
        map.put("minInclusive-valid-restriction.2 ", " minInclusive-valid-restriction.2: Error for type ''{2}''. The minInclusive value =''{0}'' must be <= maxInclusive of the base type ''{1}''.");
        map.put("minInclusive-valid-restriction.3 ", " minInclusive-valid-restriction.3: Error for type ''{2}''. The minInclusive value =''{0}'' must be > minExclusive of the base type ''{1}''.");
        map.put("minInclusive-valid-restriction.4 ", " minInclusive-valid-restriction.4: Error for type ''{2}''. The minInclusive value =''{0}'' must be < maxExclusive of the base type ''{1}''.");
        map.put("minLength-less-than-equal-to-maxLength ", " minLength-less-than-equal-to-maxLength: In the definition of {2}, value of minLength = ''{0}'' must be < value of maxLength = ''{1}''.");
        map.put("minLength-valid-restriction ", " minLength-valid-restriction: In the definition of {2}, minLength = ''{0}'' must be >= than that of the base type, ''{1}''.");
        map.put("no-xmlns ", " no-xmlns: The {name} of an attribute declaration must not match 'xmlns'.");
        map.put("no-xsi ", " no-xsi: The '{'target namespace'}' of an attribute declaration must not match ''{0}''.");
        map.put("p-props-correct.2.1 ", " p-props-correct.2.1: In the declaration of ''{0}'', the value of ''minOccurs'' is ''{1}'', but it must not be greater than the value of ''maxOccurs'', which is ''{2}''.");
        map.put("rcase-MapAndSum.1 ", " rcase-MapAndSum.1: There is not a complete functional mapping between the particles.");
        map.put("rcase-MapAndSum.2 ", " rcase-MapAndSum.2: Group''s occurrence range, ({0},{1}), is not a valid restriction of base group''s occurrence range, ({2},{3}).");
        map.put("rcase-NameAndTypeOK.1 ", " rcase-NameAndTypeOK.1: Elements have names and target namespaces which are not the same:  Element ''{0}'' in namespace ''{1}'' and element ''{2}'' in namespace ''{3}''.");
        map.put("rcase-NameAndTypeOK.2 ", " rcase-NameAndTypeOK.2: Error for the particle whose '{'term'}' is the element declaration ''{0}''. The element declaration''s '{'nillable'}' is true, but the corresponding particle in the base type has an element declaration whose '{'nillable'}' is false.");
        map.put("rcase-NameAndTypeOK.3 ", " rcase-NameAndTypeOK.3: Error for the particle whose '{'term'}' is the element declaration ''{0}''. Its occurrence range, ({1},{2}), is not a valid restriction of the range, ({3},{4}), of the corresponding particle in the base type.");
        map.put("rcase-NameAndTypeOK.4.a ", " rcase-NameAndTypeOK.4.a: Element ''{0}'' is not fixed, but the corresponding element in the base type is fixed with value ''{1}''.");
        map.put("rcase-NameAndTypeOK.4.b ", " rcase-NameAndTypeOK.4.b: Element ''{0}'' is fixed with value ''{1}'', but the corresponding element in the base type is fixed with value ''{2}''.");
        map.put("rcase-NameAndTypeOK.5 ", " rcase-NameAndTypeOK.5: Identity constraints for element ''{0}'' are not a subset of those in base.");
        map.put("rcase-NameAndTypeOK.6 ", " rcase-NameAndTypeOK.6: The disallowed substitutions for element ''{0}'' are not a superset of those in the base.");
        map.put("rcase-NameAndTypeOK.7 ", " rcase-NameAndTypeOK.7: The type of element ''{0}'', ''{1}'', is not derived from the type of the base element, ''{2}''.");
        map.put("rcase-NSCompat.1 ", " rcase-NSCompat.1: Element ''{0}'' has a namespace ''{1}'' which is not allowed by the wildcard in the base.");
        map.put("rcase-NSCompat.2 ", " rcase-NSCompat.2: Error for the particle whose '{'term'}' is the element declaration ''{0}''. Its occurrence range, ({1},{2}), is not a valid restriction of the range, ({3},{4}), of the corresponding particle in the base type.");
        map.put("rcase-NSRecurseCheckCardinality.1 ", " rcase-NSRecurseCheckCardinality.1: There is not a complete functional mapping between the particles.");
        map.put("rcase-NSRecurseCheckCardinality.2 ", " rcase-NSRecurseCheckCardinality.2: Group''s occurrence range, ({0},{1}), is not a valid restriction of base wildcard''s range, ({2},{3}).");
        map.put("rcase-NSSubset.1 ", " rcase-NSSubset.1: Wildcard is not a subset of corresponding wildcard in base.");
        map.put("rcase-NSSubset.2 ", " rcase-NSSubset.2: Wildcard''s occurrence range, ({0},{1}), is not a valid restriction of that in the base, ({2},{3}),.");
        map.put("rcase-NSSubset.3 ", " rcase-NSSubset.3: Wildcard''s process contents, ''{0}'', is weaker than that in the base, ''{1}''.");
        map.put("rcase-Recurse.1 ", " rcase-Recurse.1: Group''s occurrence range, ({0},{1}), is not a valid restriction of base group''s occurrence range, ({2},{3}).");
        map.put("rcase-Recurse.2 ", " rcase-Recurse.2: There is not a complete functional mapping between the particles.");
        map.put("rcase-RecurseLax.1 ", " rcase-RecurseLax.1: Group''s occurrence range, ({0},{1}), is not a valid restriction of base group''s occurrence range, ({2},{3}).");
        map.put("rcase-RecurseLax.2 ", " rcase-RecurseLax.2: There is not a complete functional mapping between the particles.");
        map.put("rcase-RecurseUnordered.1 ", " rcase-RecurseUnordered.1: Group''s occurrence range, ({0},{1}), is not a valid restriction of base group''s occurrence range, ({2},{3}).");
        map.put("rcase-RecurseUnordered.2 ", " rcase-RecurseUnordered.2: There is not a complete functional mapping between the particles.");
        map.put("sch-props-correct.2 ", " sch-props-correct.2: A schema cannot contain two global components with the same name; this schema contains two occurrences of ''{0}''.");
        map.put("st-props-correct.2 ", " st-props-correct.2: Circular definitions have been detected for simple type ''{0}''. This means that ''{0}'' is contained in its own type hierarchy, which is an error.");
        map.put("st-props-correct.3 ", " st-props-correct.3: Error for type ''{0}''. The value of '{'final'}' of the '{'base type definition'}', ''{1}'', forbids derivation by restriction.");
        map.put("totalDigits-valid-restriction ", " totalDigits-valid-restriction: In the definition of {2}, the value ''{0}'' for the facet ''totalDigits'' is invalid, because it must be <= the value for ''totalDigits'' which was set to ''{1}'' in one of the ancestor types.");
        map.put("whiteSpace-valid-restriction.1 ", " whiteSpace-valid-restriction.1: In the definition of {0}, the value ''{1}'' for the facet ''whitespace'' is invalid, because the value for ''whitespace'' has been set to ''collapse'' in one of the ancestor types.");
        map.put("whiteSpace-valid-restriction.2 ", " whiteSpace-valid-restriction.2: In the definition of {0}, the value ''preserve'' for the facet ''whitespace'' is invalid, because the value for ''whitespace'' has been set to ''replace'' in one of the ancestor types.");
        map.put("s4s-att-invalid-value ", " s4s-att-invalid-value: Invalid attribute value for ''{1}'' in element ''{0}''. Recorded reason: {2}");
        map.put("s4s-att-must-appear ", " s4s-att-must-appear: Attribute ''{1}'' must appear in element ''{0}''.");
        map.put("s4s-att-not-allowed ", " s4s-att-not-allowed: Attribute ''{1}'' cannot appear in element ''{0}''.");
        map.put("s4s-elt-invalid ", " s4s-elt-invalid: Element ''{0}'' is not a valid element in a schema document.");
        map.put("s4s-elt-must-match.1 ", " s4s-elt-must-match.1: The content of ''{0}'' must match {1}. A problem was found starting at: {2}.");
        map.put("s4s-elt-must-match.2 ", " s4s-elt-must-match.2: The content of ''{0}'' must match {1}. Not enough elements were found.");
        map.put("s4s-elt-invalid-content.2 ", " s4s-elt-invalid-content.2: The content of ''{0}'' is invalid.  Element ''{1}'' cannot be empty.");
        map.put("s4s-elt-invalid-content.3 ", " s4s-elt-invalid-content.3: Elements of type ''{0}'' cannot appear after declarations as children of a <schema> element.");
        map.put("s4s-elt-schema-ns ", " s4s-elt-schema-ns: The namespace of element ''{0}'' must be from the schema namespace, ''http://www.w3.org/2001/XMLSchema''.");
        map.put("s4s-elt-character ", " s4s-elt-character: Non-whitespace characters are not allowed in schema elements other than ''xs:appinfo'' and ''xs:documentation''. Saw ''{0}''.");
        map.put("c-fields-xpaths ", " c-fields-xpaths: The field value = ''{0}'' is not valid.");
        map.put("c-general-xpath ", " c-general-xpath: The expression ''{0}'' is not valid with respect to the XPath subset supported by XML Schema.");
        map.put("c-general-xpath-ns ", " c-general-xpath-ns: A namespace prefix in XPath expression ''{0}'' was not bound to a namespace.");
        map.put("c-selector-xpath ", " c-selector-xpath: The selector value = ''{0}'' is not valid; selector xpaths cannot contain attributes.");
        map.put("EmptyTargetNamespace ", " EmptyTargetNamespace: In schema document ''{0}'', the value of the ''targetNamespace'' attribute cannot be an empty string.");
        map.put("FacetValueFromBase ", " FacetValueFromBase: In the declaration of type ''{0}'', value ''{1}'' of facet ''{2}'' must be from the value space of the base type, ''{3}''.");
        map.put("FixedFacetValue ", " FixedFacetValue: In the definition of {3}, the value ''{1}'' for the facet ''{0}'' is invalid, because the value for ''{0}'' has been set to ''{2}'' in one of the ancestor types, and '{'fixed'}' = true.");
        map.put("InvalidRegex ", " InvalidRegex: Pattern value ''{0}'' is not a valid regular expression. The reported error was: ''{1}''.");
        map.put("maxOccurLimit ", " Current configuration of the parser doesn''t allow the expansion of a content model for a complex type to contain more than {0} nodes.");
        map.put("PublicSystemOnNotation ", " PublicSystemOnNotation: At least one of 'public' and 'system' must appear in element 'notation'.");
        map.put("SchemaLocation ", " SchemaLocation: schemaLocation value = ''{0}'' must have even number of URI''s.");
        map.put("TargetNamespace.1 ", " TargetNamespace.1: Expecting namespace ''{0}'', but the target namespace of the schema document is ''{1}''.");
        map.put("TargetNamespace.2 ", " TargetNamespace.2: Expecting no namespace, but the schema document has a target namespace of ''{1}''.");
        map.put("UndeclaredEntity ", " UndeclaredEntity: Entity ''{0}'' is not declared.");
        map.put("UndeclaredPrefix ", " UndeclaredPrefix: Cannot resolve ''{0}'' as a QName: the prefix ''{1}'' is not declared.");
        map.put("jaxp12-schema-source-type.1 ", " The ''http://java.sun.com/xml/jaxp/properties/schemaSource'' property cannot have a value of type ''{0}''. Possible types of the value supported are String, File, InputStream, InputSource or an array of these types.");
        map.put("jaxp12-schema-source-type.2 ", " The ''http://java.sun.com/xml/jaxp/properties/schemaSource'' property cannot have an array value of type ''{0}''. Possible types of the array supported are Object, String, File, InputStream and InputSource.");
        map.put("jaxp12-schema-source-ns ", " When using an array of Objects as the value of the 'http://java.sun.com/xml/jaxp/properties/schemaSource' property, it is illegal to have two schemas that share the same target namespace.");
        return Map.copyOf(map);
    }

    // Original XMLSchemaMessages.properties file
    // @formatter:off
    /*

# This file contains error and warning messages related to XML Schema
# The messages are arranged in key and value tuples in a ListResourceBundle.
#
# @version $Id: XMLSchemaMessages.properties 806363 2009-08-20 21:18:48Z mrglavas $

        BadMessageKey = The error message corresponding to the message key can not be found.
        FormatFailed = An internal error occurred while formatting the following message:\n

# For internal use

        Internal-Error = Internal error: {0}.
        dt-whitespace = Whitespace facet value is not available for the union simpleType ''{0}''
        GrammarConflict = One of the grammar(s) returned from the user's grammar pool is in conflict with another grammar.

# Identity constraints

        AbsentKeyValue = cvc-identity-constraint.4.2.1.a: Element \"{0}\" has no value for the key \"{1}\".
        DuplicateField = Duplicate match in scope for field \"{0}\".
        DuplicateKey = cvc-identity-constraint.4.2.2: Duplicate key value [{0}] declared for identity constraint \"{2}\" of element \"{1}\".
        DuplicateUnique = cvc-identity-constraint.4.1: Duplicate unique value [{0}] declared for identity constraint \"{2}\" of element \"{1}\".
        FieldMultipleMatch = cvc-identity-constraint.3: Field \"{0}\" of identity constraint \"{1}\" matches more than one value within the scope of its selector; fields must match unique values.
        FixedDiffersFromActual = The content of this element is not equivalent to the value of the \"fixed\" attribute in the element's declaration in the schema.
        KeyMatchesNillable = cvc-identity-constraint.4.2.3: Element \"{0}\" has the key \"{1}\" which matches an element which has nillable set to true.
        KeyNotEnoughValues = cvc-identity-constraint.4.2.1.b: Not enough values specified for <key name=\"{1}\"> identity constraint specified for element \"{0}\".
        KeyNotFound = cvc-identity-constraint.4.3: Key ''{0}'' with value ''{1}'' not found for identity constraint of element ''{2}''.
        KeyRefOutOfScope = Identity Constraint error:  identity constraint \"{0}\" has a keyref which refers to a key or unique that is out of scope.
        KeyRefReferNotFound = Key reference declaration \"{0}\" refers to unknown key with name \"{1}\".
        UnknownField = Internal identity constraint error; unknown field \"{0}\" for identity constraint \"{2}\" specified for element \"{1}\".

# Ideally, we should only use the following error keys, not the ones under
# "Identity constraints". And we should cover all of the following errors.

#validation (3.X.4)

        cvc-attribute.3 = cvc-attribute.3: The value ''{2}'' of attribute ''{1}'' on element ''{0}'' is not valid with respect to its type, ''{3}''.
        cvc-attribute.4 = cvc-attribute.4: The value ''{2}'' of attribute ''{1}'' on element ''{0}'' is not valid with respect to its fixed '{'value constraint'}'. The attribute must have a value of ''{3}''.
        cvc-complex-type.2.1 = cvc-complex-type.2.1: Element ''{0}'' must have no character or element information item [children], because the type''s content type is empty.
        cvc-complex-type.2.2 = cvc-complex-type.2.2: Element ''{0}'' must have no element [children], and the value must be valid.
        cvc-complex-type.2.3 = cvc-complex-type.2.3: Element ''{0}'' cannot have character [children], because the type''s content type is element-only.
        cvc-complex-type.2.4.a = cvc-complex-type.2.4.a: Invalid content was found starting with element ''{0}''. One of ''{1}'' is expected.
        cvc-complex-type.2.4.b = cvc-complex-type.2.4.b: The content of element ''{0}'' is not complete. One of ''{1}'' is expected.
        cvc-complex-type.2.4.c = cvc-complex-type.2.4.c: The matching wildcard is strict, but no declaration can be found for element ''{0}''.
        cvc-complex-type.2.4.d = cvc-complex-type.2.4.d: Invalid content was found starting with element ''{0}''. No child element is expected at this point.
        cvc-complex-type.2.4.e = cvc-complex-type.2.4.e: ''{0}'' can occur a maximum of ''{2}'' times in the current sequence. This limit was exceeded. At this point one of ''{1}'' is expected.
        cvc-complex-type.2.4.f = cvc-complex-type.2.4.f: ''{0}'' can occur a maximum of ''{1}'' times in the current sequence. This limit was exceeded. No child element is expected at this point.
        cvc-complex-type.2.4.g = cvc-complex-type.2.4.g: Invalid content was found starting with element ''{0}''. ''{1}'' is expected to occur a minimum of ''{2}'' times in the current sequence. One more instance is required to satisfy this constraint.
        cvc-complex-type.2.4.h = cvc-complex-type.2.4.h: Invalid content was found starting with element ''{0}''. ''{1}'' is expected to occur a minimum of ''{2}'' times in the current sequence. ''{3}'' more instances are required to satisfy this constraint.
        cvc-complex-type.2.4.i = cvc-complex-type.2.4.i: The content of element ''{0}'' is not complete. ''{1}'' is expected to occur a minimum of ''{2}'' times. One more instance is required to satisfy this constraint.
        cvc-complex-type.2.4.j = cvc-complex-type.2.4.j: The content of element ''{0}'' is not complete. ''{1}'' is expected to occur a minimum of ''{2}'' times. ''{3}'' more instances are required to satisfy this constraint.
        cvc-complex-type.3.1 = cvc-complex-type.3.1: Value ''{2}'' of attribute ''{1}'' of element ''{0}'' is not valid with respect to the corresponding attribute use. Attribute ''{1}'' has a fixed value of ''{3}''.
        cvc-complex-type.3.2.1 = cvc-complex-type.3.2.1: Element ''{0}'' does not have an attribute wildcard for attribute ''{1}''.
        cvc-complex-type.3.2.2 = cvc-complex-type.3.2.2: Attribute ''{1}'' is not allowed to appear in element ''{0}''.
        cvc-complex-type.4 = cvc-complex-type.4: Attribute ''{1}'' must appear on element ''{0}''.
        cvc-complex-type.5.1 = cvc-complex-type.5.1: In element ''{0}'', attribute ''{1}'' is a Wild ID. But there is already a Wild ID ''{2}''. There can be only one.
        cvc-complex-type.5.2 = cvc-complex-type.5.2: In element ''{0}'', attribute ''{1}'' is a Wild ID. But there is already an attribute ''{2}'' derived from ID among the '{'attribute uses'}'.
        cvc-datatype-valid.1.2.1 = cvc-datatype-valid.1.2.1: ''{0}'' is not a valid value for ''{1}''.
        cvc-datatype-valid.1.2.2 = cvc-datatype-valid.1.2.2: ''{0}'' is not a valid value of list type ''{1}''.
        cvc-datatype-valid.1.2.3 = cvc-datatype-valid.1.2.3: ''{0}'' is not a valid value of union type ''{1}''.
        cvc-elt.1.a = cvc-elt.1.a: Cannot find the declaration of element ''{0}''.
        cvc-elt.1.b = cvc-elt.1.b: The name of the element does not match the name of the element declaration. Saw ''{0}''. Expected ''{1}''.
        cvc-elt.2 = cvc-elt.2: The value of '{'abstract'}' in the element declaration for ''{0}'' must be false.
        cvc-elt.3.1 = cvc-elt.3.1: Attribute ''{1}'' must not appear on element ''{0}'', because the '{'nillable'}' property of ''{0}'' is false.
        cvc-elt.3.2.1 = cvc-elt.3.2.1: Element ''{0}'' cannot have character or element information [children], because ''{1}'' is specified.
        cvc-elt.3.2.2 = cvc-elt.3.2.2: There must be no fixed '{'value constraint'}' for element ''{0}'', because ''{1}'' is specified.
        cvc-elt.4.1 = cvc-elt.4.1: The value ''{2}'' of attribute ''{1}'' of element ''{0}'' is not a valid QName.
        cvc-elt.4.2 = cvc-elt.4.2: Cannot resolve ''{1}'' to a type definition for element ''{0}''.
        cvc-elt.4.3 = cvc-elt.4.3: Type ''{1}'' is not validly derived from the type definition, ''{2}'', of element ''{0}''.
        cvc-elt.5.1.1 = cvc-elt.5.1.1: '{'value constraint'}' ''{2}'' of element ''{0}'' is not a valid default value for type ''{1}''.
        cvc-elt.5.2.2.1 = cvc-elt.5.2.2.1: Element ''{0}'' must have no element information item [children].
        cvc-elt.5.2.2.2.1 = cvc-elt.5.2.2.2.1: The value ''{1}'' of element ''{0}'' does not match the fixed '{'value constraint'}' value ''{2}''.
        cvc-elt.5.2.2.2.2 = cvc-elt.5.2.2.2.2: The value ''{1}'' of element ''{0}'' does not match the '{'value constraint'}' value ''{2}''.
        cvc-enumeration-valid = cvc-enumeration-valid: Value ''{0}'' is not facet-valid with respect to enumeration ''{1}''. It must be a value from the enumeration.
        cvc-fractionDigits-valid = cvc-fractionDigits-valid: Value ''{0}'' has {1} fraction digits, but the number of fraction digits has been limited to {2}.
        cvc-id.1 = cvc-id.1: There is no ID/IDREF binding for IDREF ''{0}''.
        cvc-id.2 = cvc-id.2: There are multiple occurrences of ID value ''{0}''.
        cvc-id.3 = cvc-id.3: A field of identity constraint ''{0}'' matched element ''{1}'', but this element does not have a simple type.
        cvc-length-valid = cvc-length-valid: Value ''{0}'' with length = ''{1}'' is not facet-valid with respect to length ''{2}'' for type ''{3}''.
        cvc-maxExclusive-valid = cvc-maxExclusive-valid: Value ''{0}'' is not facet-valid with respect to maxExclusive ''{1}'' for type ''{2}''.
        cvc-maxInclusive-valid = cvc-maxInclusive-valid: Value ''{0}'' is not facet-valid with respect to maxInclusive ''{1}'' for type ''{2}''.
        cvc-maxLength-valid = cvc-maxLength-valid: Value ''{0}'' with length = ''{1}'' is not facet-valid with respect to maxLength ''{2}'' for type ''{3}''.
        cvc-minExclusive-valid = cvc-minExclusive-valid: Value ''{0}'' is not facet-valid with respect to minExclusive ''{1}'' for type ''{2}''.
        cvc-minInclusive-valid = cvc-minInclusive-valid: Value ''{0}'' is not facet-valid with respect to minInclusive ''{1}'' for type ''{2}''.
        cvc-minLength-valid = cvc-minLength-valid: Value ''{0}'' with length = ''{1}'' is not facet-valid with respect to minLength ''{2}'' for type ''{3}''.
        cvc-pattern-valid = cvc-pattern-valid: Value ''{0}'' is not facet-valid with respect to pattern ''{1}'' for type ''{2}''.
        cvc-totalDigits-valid = cvc-totalDigits-valid: Value ''{0}'' has {1} total digits, but the number of total digits has been limited to {2}.
        cvc-type.1 = cvc-type.1: The type definition ''{0}'' was not found.
        cvc-type.2 = cvc-type.2: The type definition cannot be abstract for element {0}.
        cvc-type.3.1.1 = cvc-type.3.1.1: Element ''{0}'' is a simple type, so it cannot have attributes, excepting those whose namespace name is identical to ''http://www.w3.org/2001/XMLSchema-instance'' and whose [local name] is one of ''type'', ''nil'', ''schemaLocation'' or ''noNamespaceSchemaLocation''. However, the attribute, ''{1}'' was found.
        cvc-type.3.1.2 = cvc-type.3.1.2: Element ''{0}'' is a simple type, so it must have no element information item [children].
        cvc-type.3.1.3 = cvc-type.3.1.3: The value ''{1}'' of element ''{0}'' is not valid.

#schema valid (3.X.3)

        schema_reference.4 = schema_reference.4: Failed to read schema document ''{0}'', because 1) could not find the document; 2) the document could not be read; 3) the root element of the document is not <xsd:schema>.
        src-annotation = src-annotation: <annotation> elements can only contain <appinfo> and <documentation> elements, but ''{0}'' was found.
        src-attribute.1 = src-attribute.1: The properties ''default'' and ''fixed'' cannot both be present in attribute declaration ''{0}''. Use only one of them.
        src-attribute.2 = src-attribute.2: : The property ''default'' is present in attribute ''{0}'', so the value of ''use'' must be ''optional''.
        src-attribute.3.1 = src-attribute.3.1: One of 'ref' or 'name' must be present in a local attribute declaration.
        src-attribute.3.2 = src-attribute.3.2: The content must match (annotation?) for the attribute reference ''{0}''.
        src-attribute.4 = src-attribute.4: Attribute ''{0}'' has both a ''type'' attribute and an anonymous ''simpleType'' child. Only one of these is allowed for an attribute.
        src-attribute_group.2 = src-attribute_group.2: The intersection of wildcards is not expressible for attribute group ''{0}''.
        src-attribute_group.3 = src-attribute_group.3: Circular definitions detected for attribute group ''{0}''. Recursively following attribute group references eventually leads back to itself.
        src-ct.1 = src-ct.1: Complex Type Definition Representation Error for type ''{0}''.  When <complexContent> is used, the base type must be a complexType. ''{1}'' is a simpleType.
        src-ct.2.1 = src-ct.2.1: Complex Type Definition Representation Error for type ''{0}''.  When <simpleContent> is used, the base type must be a complexType whose content type is simple, or, only if restriction is specified, a complex type with mixed content and emptiable particle, or, only if extension is specified, a simple type. ''{1}'' satisfies none of these conditions.
        src-ct.2.2 = src-ct.2.2: Complex Type Definition Representation Error for type ''{0}''.  When a complexType with simpleContent restricts a complexType with mixed content and emptiable particle, then there must be a <simpleType> among the children of <restriction>.
        src-ct.4 = src-ct.4: Complex Type Definition Representation Error for type ''{0}''. The intersection of wildcards is not expressible.
        src-ct.5 = src-ct.5: Complex Type Definition Representation Error for type ''{0}''. The union of wildcards is not expressible.
        src-element.1 = src-element.1: The properties ''default'' and ''fixed'' cannot both be present in element declaration ''{0}''. Use only one of them.
        src-element.2.1 = src-element.2.1: : One of 'ref' or 'name' must be present in a local element declaration.
        src-element.2.2 = src-element.2.2: Since ''{0}'' contains the ''ref'' attribute, its content must match (annotation?). However, ''{1}'' was found.
        src-element.3 = src-element.3: Element ''{0}'' has both a ''type'' attribute and a ''anonymous type'' child. Only one of these is allowed for an element.
        src-import.1.1 = src-import.1.1: The namespace attribute ''{0}'' of an <import> element information item must not be the same as the targetNamespace of the schema it exists in.
        src-import.1.2 = src-import.1.2: If the namespace attribute is not present on an <import> element information item then the enclosing schema must have a targetNamespace.
        src-import.2 = src-import.2: The root element of document ''{0}'' has to have the namespace name ''http://www.w3.org/2001/XMLSchema'' and the local name ''schema''.
        src-import.3.1 = src-import.3.1: The namespace attribute, ''{0}'', of an <import> element information item must be identical to the targetNamespace attribute, ''{1}'', of the imported document.
        src-import.3.2 = src-import.3.2: An <import> element information item that had no namespace attribute was found, so the imported document cannot have a targetNamespace attribute. However, the targetNamespace ''{1}'' was found in the imported document.
        src-include.1 = src-include.1: The root element of document ''{0}'' has to have the namespace name ''http://www.w3.org/2001/XMLSchema'' and the local name ''schema''.
        src-include.2.1 = src-include.2.1: The targetNamespace of the referenced schema, currently ''{1}'', must be identical to that of the including schema, currently ''{0}''.
        src-redefine.2 = src-redefine.2: The root element of document ''{0}'' has to have the namespace name ''http://www.w3.org/2001/XMLSchema'' and the local name ''schema''.
        src-redefine.3.1 = src-redefine.3.1: The targetNamespace of the referenced schema, currently ''{1}'', must be identical to that of the redefining schema, currently ''{0}''.
        src-redefine.5.a.a = src-redefine.5.a.a: No non-annotation children of <simpleType> were found. <simpleType> children of <redefine> elements must have <restriction> descendants, with 'base' attributes that refer to themselves.
        src-redefine.5.a.b = src-redefine.5.a.b: ''{0}'' is not a valid child element. <simpleType> children of <redefine> elements must have <restriction> descendants, with ''base'' attributes that refer to themselves.
        src-redefine.5.a.c = src-redefine.5.a.c: ''{0}'' does not have a ''base'' attribute that refers to the redefined element, ''{1}''. <simpleType> children of <redefine> elements must have <restriction> descendants, with ''base'' attributes that refer to themselves.
        src-redefine.5.b.a = src-redefine.5.b.a: No non-annotation children of <complexType> were found. <complexType> children of <redefine> elements must have <extension> or <restriction> descendants, with 'base' attributes that refer to themselves.
        src-redefine.5.b.b = src-redefine.5.b.b: No non-annotation grandchildren of <complexType> were found. <complexType> children of <redefine> elements must have <extension> or <restriction> descendants, with 'base' attributes that refer to themselves.
        src-redefine.5.b.c = src-redefine.5.b.c: ''{0}'' is not a valid grandchild element. <complexType> children of <redefine> elements must have <extension> or <restriction> descendants, with ''base'' attributes that refer to themselves.
        src-redefine.5.b.d = src-redefine.5.b.d: ''{0}'' does not have a ''base'' attribute that refers to the redefined element, ''{1}''. <complexType> children of <redefine> elements must have <extension> or <restriction> descendants, with ''base'' attributes that refer to themselves.
        src-redefine.6.1.1 = src-redefine.6.1.1:  If a group child of a <redefine> element contains a group referring itself, it must have exactly 1; this one has ''{0}''.
        src-redefine.6.1.2 = src-redefine.6.1.2:  The group ''{0}'', which contains a reference to a group being redefined, must have ''minOccurs'' = ''maxOccurs'' = 1.
        src-redefine.6.2.1 = src-redefine.6.2.1: No group in the redefined schema has a name matching ''{0}''.
        src-redefine.6.2.2 = src-redefine.6.2.2: Group ''{0}'' does not properly restrict the group it redefines; constraint violated:  ''{1}''.
        src-redefine.7.1 = src-redefine.7.1:  If an attributeGroup child of a <redefine> element contains an attributeGroup referring itself, it must have exactly 1; this one has {0}.
        src-redefine.7.2.1 = src-redefine.7.2.1: No attributeGroup in the redefined schema has a name matching ''{0}''.
        src-redefine.7.2.2 = src-redefine.7.2.2: AttributeGroup ''{0}'' does not properly restrict the attributeGroup it redefines; constraint violated:  ''{1}''.
        src-resolve = src-resolve: Cannot resolve the name ''{0}'' to a(n) ''{1}'' component.
        src-resolve.4.1 = src-resolve.4.1: Error resolving component ''{2}''. It was detected that ''{2}'' has no namespace, but components with no target namespace are not referenceable from schema document ''{0}''. If ''{2}'' is intended to have a namespace, perhaps a prefix needs to be provided. If it is intended that ''{2}'' has no namespace, then an ''import'' without a "namespace" attribute should be added to ''{0}''.
        src-resolve.4.2 = src-resolve.4.2: Error resolving component ''{2}''. It was detected that ''{2}'' is in namespace ''{1}'', but components from this namespace are not referenceable from schema document ''{0}''. If this is the incorrect namespace, perhaps the prefix of ''{2}'' needs to be changed. If this is the correct namespace, then an appropriate ''import'' tag should be added to ''{0}''.
        src-simple-type.2.a = src-simple-type.2.a: A <restriction> element was found that has both a base [attribute] and a <simpleType> element among its [children]. Only one is allowed.
        src-simple-type.2.b = src-simple-type.2.b: A <restriction> element was found that has neither a base [attribute] nor a <simpleType> element among its [children]. One is required.
        src-simple-type.3.a = src-simple-type.3.a: A <list> element was found that has both an itemType [attribute] and a <simpleType> element among its [children]. Only one is allowed.
        src-simple-type.3.b = src-simple-type.3.b: A <list> element was found that has neither an itemType [attribute] nor a <simpleType> element among its [children]. One is required.
        src-single-facet-value = src-single-facet-value: The facet ''{0}'' is defined more than once.
        src-union-memberTypes-or-simpleTypes = src-union-memberTypes-or-simpleTypes: A <union> element must have either a non-empty memberTypes [attribute] or at least one <simpleType> element among its [children].

#constraint valid (3.X.6)

        ag-props-correct.2 = ag-props-correct.2: Error for attribute group ''{0}''.  Duplicate attribute uses with the same name and target namespace are specified.  Name of duplicate attribute use is ''{1}''.
        ag-props-correct.3 = ag-props-correct.3: Error for attribute group ''{0}''.  Two attribute declarations, ''{1}'' and ''{2}'' have types which are derived from ID.
        a-props-correct.2 = a-props-correct.2: Invalid value constraint value ''{1}'' in attribute ''{0}''.
        a-props-correct.3 = a-props-correct.3: Attribute ''{0}'' cannot use ''fixed'' or ''default'', because the attribute''s '{'type definition'}' is ID, or is derived from ID.
        au-props-correct.2 = au-props-correct.2: In the attribute declaration of ''{0}'', a fixed value of ''{1}'' was specified. So if the attribute use referring to ''{0}'' also has a '{'value constraint'}', it must be fixed and its value must be ''{1}''.
        cos-all-limited.1.2 = cos-all-limited.1.2: An 'all' model group must appear in a particle with '{'min occurs'}' = '{'max occurs'}' = 1, and that particle must be part of a pair which constitutes the '{'content type'}' of a complex type definition.
        cos-all-limited.2 = cos-all-limited.2: The '{'max occurs'}' of an element in an ''all'' model group must be 0 or 1. The value ''{0}'' for element ''{1}'' is invalid.
        cos-applicable-facets = cos-applicable-facets: Facet ''{0}'' is not allowed by type {1}.
        cos-ct-extends.1.1 = cos-ct-extends.1.1: Type ''{0}'' was derived by extension from type ''{1}''.  However, the ''final'' attribute of ''{1}'' forbids derivation by extension.
        cos-ct-extends.1.4.3.2.2.1.a = cos-ct-extends.1.4.3.2.2.1.a: The content type of a derived type and that of its base must both be mixed or both be element-only. Type ''{0}'' is element only, but its base type is not.
        cos-ct-extends.1.4.3.2.2.1.b = cos-ct-extends.1.4.3.2.2.1.b: The content type of a derived type and that of its base must both be mixed or both be element-only. Type ''{0}'' is mixed, but its base type is not.
        cos-element-consistent = cos-element-consistent: Error for type ''{0}''. Multiple elements with name ''{1}'', with different types, appear in the model group.
        cos-list-of-atomic = cos-list-of-atomic: In the definition of list type ''{0}'', type ''{1}'' is an invalid list element type because it is not atomic (''{1}'' is either a list type, or a union type which contains a list).
        cos-nonambig = cos-nonambig: {0} and {1} (or elements from their substitution group) violate \"Unique Particle Attribution\". During validation against this schema, ambiguity would be created for those two particles.
        cos-particle-restrict.a = cos-particle-restrict.a: Derived particle is empty, and base is not emptiable.
        cos-particle-restrict.b = cos-particle-restrict.b: Base particle is empty, but derived particle is not.
        cos-particle-restrict.2 = cos-particle-restrict.2: Forbidden particle restriction: ''{0}''.
        cos-st-restricts.1.1 = cos-st-restricts.1.1: The type ''{1}'' is atomic, so its '{'base type definition'}', ''{0}'', must be an atomic simple type definition or a built-in primitive datatype.
        cos-st-restricts.2.1 = cos-st-restricts.2.1: In the definition of list type ''{0}'', type ''{1}'' is an invalid item type because it is either a list type, or a union type that contains a list.
        cos-st-restricts.2.3.1.1 = cos-st-restricts.2.3.1.1: The '{'final'}' component of the '{'item type definition'}', ''{0}'', contains ''list''. This means that ''{0}'' cannot be used as an item type for list type ''{1}''.
        cos-st-restricts.3.3.1.1 = cos-st-restricts.3.3.1.1: The '{'final'}' component of the '{'member type definitions'}', ''{0}'', contains ''union''. This means that ''{0}'' cannot be used as an member type for union type ''{1}''.
        cos-valid-default.2.1 = cos-valid-default.2.1: Element ''{0}'' has a value constraint and must have a mixed or simple content model.
        cos-valid-default.2.2.2 = cos-valid-default.2.2.2: Since element ''{0}'' has a '{'value constraint'}' and its type definition has mixed '{'content type'}', then the particle of the '{'content type'}' must be emptiable.
        c-props-correct.2 = c-props-correct.2: Cardinality of Fields for keyref ''{0}'' and key ''{1}'' must match each other.
        ct-props-correct.3 = ct-props-correct.3: Circular definitions detected for complex type ''{0}''. This means that ''{0}'' is contained in its own type hierarchy, which is an error.
        ct-props-correct.4 = ct-props-correct.4: Error for type ''{0}''. Duplicate attribute uses with the same name and target namespace are specified.  Name of duplicate attribute use is ''{1}''.
        ct-props-correct.5 = ct-props-correct.5: Error for type ''{0}''. Two attribute declarations, ''{1}'' and ''{2}'' have types which are derived from ID.
        derivation-ok-restriction.1 = derivation-ok-restriction.1: Type ''{0}'' was derived by restriction from type ''{1}''.  However, ''{1}'' has a '{'final'}' property that forbids derivation by restriction.
        derivation-ok-restriction.2.1.1 = derivation-ok-restriction.2.1.1: Error for type ''{0}''.  The attribute use ''{1}'' in this type has a ''use'' value of ''{2}'', which is inconsistent with the value of ''required'' in a matching attribute use in the base type.
        derivation-ok-restriction.2.1.2 = derivation-ok-restriction.2.1.2: Error for type ''{0}''.  The attribute use ''{1}'' in this type has type ''{2}'', which is not validly derived from ''{3}'', the type of the matching attribute use in the base type.
        derivation-ok-restriction.2.1.3.a = derivation-ok-restriction.2.1.3.a: Error for type ''{0}''.  The attribute use ''{1}'' in this type has an effective value constraint which is not fixed, and the effective value constraint of the matching attribute use in the base type is fixed.
        derivation-ok-restriction.2.1.3.b = derivation-ok-restriction.2.1.3.b: Error for type ''{0}''.  The attribute use ''{1}'' in this type has an effective value constraint fixed with a value of ''{2}'', which is not consistent with the value of ''{3}'' for the fixed effective value constraint of the matching attribute use in the base type.
        derivation-ok-restriction.2.2.a = derivation-ok-restriction.2.2.a: Error for type ''{0}''.  The attribute use ''{1}'' in this type does not have a matching attribute use in the base, and the base type does not have a wildcard attribute.
        derivation-ok-restriction.2.2.b = derivation-ok-restriction.2.2.b: Error for type ''{0}''.  The attribute use ''{1}'' in this type does not have a matching attribute use in the base, and the wildcard in the base type does not allow the namespace ''{2}'' of this attribute use.
        derivation-ok-restriction.3 = derivation-ok-restriction.3: Error for type ''{0}''.  The attribute use ''{1}'' in the base type has REQUIRED as true, but there is no matching attribute use in the derived type.
        derivation-ok-restriction.4.1 = derivation-ok-restriction.4.1: Error for type ''{0}''.  The derivation has an attribute wildcard, but the base does not have one.
        derivation-ok-restriction.4.2 = derivation-ok-restriction.4.2: Error for type ''{0}''.  The wildcard in the derivation is not a valid wildcard subset of the one in the base.
        derivation-ok-restriction.4.3 = derivation-ok-restriction.4.3: Error for type ''{0}''.  The process contents of the wildcard in the derivation ({1}) is weaker than that in the base ({2}).
        derivation-ok-restriction.5.2.2.1 = derivation-ok-restriction.5.2.2.1: Error for type ''{0}''.  The simple content type of this type, ''{1}'', is not a valid restriction of the simple content type of the base, ''{2}''.
        derivation-ok-restriction.5.3.2 = derivation-ok-restriction.5.3.2: Error for type ''{0}''.  The content type of this type is empty, but the content type of the base, ''{1}'', is not empty or emptiable.
        derivation-ok-restriction.5.4.1.2 = derivation-ok-restriction.5.4.1.2: Error for type ''{0}''.  The content type of this type is mixed, but the content type of the base, ''{1}'', is not.
        derivation-ok-restriction.5.4.2 = derivation-ok-restriction.5.4.2: Error for type ''{0}''.  The particle of the type is not a valid restriction of the particle of the base.
        enumeration-required-notation = enumeration-required-notation: The NOTATION type, ''{0}'' used by {2} ''{1}'', must have an enumeration facet value which specifies the notation elements used by this type.
        enumeration-valid-restriction = enumeration-valid-restriction: Enumeration value ''{0}'' is not in the value space of the base type, {1}.
        e-props-correct.2 = e-props-correct.2: Invalid value constraint value ''{1}'' in element ''{0}''.
        e-props-correct.4 = e-props-correct.4: The '{'type definition'}' of element ''{0}'' is not validly derived from the '{'type definition'}' of the substitutionHead ''{1}'', or the '{'substitution group exclusions'}' property of ''{1}'' does not allow this derivation.
        e-props-correct.5 = e-props-correct.5: A '{'value constraint'}' must not be present on element ''{0}'', because the element''s '{'type definition'}' or '{'type definition'}'''s '{'content type'}' is ID, or is derived from ID.
        e-props-correct.6 = e-props-correct.6: Circular substitution group detected for element ''{0}''.
        fractionDigits-valid-restriction = fractionDigits-valid-restriction: In the definition of {2}, the value ''{0}'' for the facet ''fractionDigits'' is invalid, because it must be <= the value for ''fractionDigits'' which was set to ''{1}'' in one of the ancestor types.
        fractionDigits-totalDigits = fractionDigits-totalDigits: In the definition of {2}, the value ''{0}'' for the facet ''fractionDigits'' is invalid, because the value must be <= the value for ''totalDigits'' which is ''{1}''.
        length-minLength-maxLength.1.1 = length-minLength-maxLength.1.1: For type {0}, it is an error for the value of length ''{1}'' to be less than the value of minLength ''{2}''.
        length-minLength-maxLength.1.2.a = length-minLength-maxLength.1.2.a: For type {0}, it is an error for the base to not have a minLength facet if the current restriction has the minLength facet and the current restriction or base has the length facet.
        length-minLength-maxLength.1.2.b = length-minLength-maxLength.1.2.b: For type {0}, it is an error for the current minLength ''{1}'' to not equal the base minLength ''{2}''.
        length-minLength-maxLength.2.1 = length-minLength-maxLength.2.1: For type {0}, it is an error for the value of length ''{1}'' to be greater than the value of maxLength ''{2}''.
        length-minLength-maxLength.2.2.a = length-minLength-maxLength.2.2.a: For type {0}, it is an error for the base to not have a maxLength facet if the current restriction has the maxLength facet and the current restriction or base has the length facet.
        length-minLength-maxLength.2.2.b = length-minLength-maxLength.2.2.b: For type {0}, it is an error for the current maxLength ''{1}'' to not equal the base maxLength ''{2}''.
        length-valid-restriction = length-valid-restriction: Error for type ''{2}''. The value of length = ''{0}'' must be = the value of that of the base type ''{1}''.
        maxExclusive-valid-restriction.1 = maxExclusive-valid-restriction.1: Error for type ''{2}''. The maxExclusive value =''{0}'' must be <= maxExclusive of the base type ''{1}''.
        maxExclusive-valid-restriction.2 = maxExclusive-valid-restriction.2: Error for type ''{2}''. The maxExclusive value =''{0}'' must be <= maxInclusive of the base type ''{1}''.
        maxExclusive-valid-restriction.3 = maxExclusive-valid-restriction.3: Error for type ''{2}''. The maxExclusive value =''{0}'' must be > minInclusive of the base type ''{1}''.
        maxExclusive-valid-restriction.4 = maxExclusive-valid-restriction.4: Error for type ''{2}''. The maxExclusive value =''{0}'' must be > minExclusive of the base type ''{1}''.
        maxInclusive-maxExclusive = maxInclusive-maxExclusive: It is an error for both maxInclusive and maxExclusive to be specified for the same datatype. In {2}, maxInclusive = ''{0}'' and maxExclusive = ''{1}''.
        maxInclusive-valid-restriction.1 = maxInclusive-valid-restriction.1: Error for type ''{2}''. The maxInclusive value =''{0}'' must be <= maxInclusive of the base type ''{1}''.
        maxInclusive-valid-restriction.2 = maxInclusive-valid-restriction.2: Error for type ''{2}''. The maxInclusive value =''{0}'' must be < maxExclusive of the base type ''{1}''.
        maxInclusive-valid-restriction.3 = maxInclusive-valid-restriction.3: Error for type ''{2}''. The maxInclusive value =''{0}'' must be >= minInclusive of the base type ''{1}''.
        maxInclusive-valid-restriction.4 = maxInclusive-valid-restriction.4: Error for type ''{2}''. The maxInclusive value =''{0}'' must be > minExclusive of the base type ''{1}''.
        maxLength-valid-restriction = maxLength-valid-restriction: In the definition of {2}, maxLength value = ''{0}'' must be <= that of the base type ''{1}''.
        mg-props-correct.2 = mg-props-correct.2: Circular definitions detected for group ''{0}''. Recursively following the '{'term'}' values of the particles leads to a particle whose '{'term'}' is the group itself.
        minExclusive-less-than-equal-to-maxExclusive = minExclusive-less-than-equal-to-maxExclusive: In the definition of {2}, minExclusive value = ''{0}'' must be <= maxExclusive value = ''{1}''.
        minExclusive-less-than-maxInclusive = minExclusive-less-than-maxInclusive: In the definition of {2}, minExclusive value = ''{0}'' must be < maxInclusive value = ''{1}''.
        minExclusive-valid-restriction.1 = minExclusive-valid-restriction.1: Error for type ''{2}''. The minExclusive value =''{0}'' must be >= minExclusive of the base type ''{1}''.
        minExclusive-valid-restriction.2 = minExclusive-valid-restriction.2: Error for type ''{2}''. The minExclusive value =''{0}'' must be <= maxInclusive of the base type ''{1}''.
        minExclusive-valid-restriction.3 = minExclusive-valid-restriction.3: Error for type ''{2}''. The minExclusive value =''{0}'' must be >= minInclusive of the base type ''{1}''.
        minExclusive-valid-restriction.4 = minExclusive-valid-restriction.4: Error for type ''{2}''. The minExclusive value =''{0}'' must be < maxExclusive of the base type ''{1}''.
        minInclusive-less-than-equal-to-maxInclusive = minInclusive-less-than-equal-to-maxInclusive: In the definition of {2}, minInclusive value = ''{0}'' must be <= maxInclusive value = ''{1}''.
        minInclusive-less-than-maxExclusive = minInclusive-less-than-maxExclusive: In the definition of {2}, minInclusive value = ''{0}'' must be < maxExclusive value = ''{1}''.
        minInclusive-minExclusive = minInclusive-minExclusive: It is an error for both minInclusive and minExclusive to be specified for the same datatype. In {2}, minInclusive = ''{0}'' and minExclusive = ''{1}''.
        minInclusive-valid-restriction.1 = minInclusive-valid-restriction.1: Error for type ''{2}''. The minInclusive value =''{0}'' must be >= minInclusive of the base type ''{1}''.
        minInclusive-valid-restriction.2 = minInclusive-valid-restriction.2: Error for type ''{2}''. The minInclusive value =''{0}'' must be <= maxInclusive of the base type ''{1}''.
        minInclusive-valid-restriction.3 = minInclusive-valid-restriction.3: Error for type ''{2}''. The minInclusive value =''{0}'' must be > minExclusive of the base type ''{1}''.
        minInclusive-valid-restriction.4 = minInclusive-valid-restriction.4: Error for type ''{2}''. The minInclusive value =''{0}'' must be < maxExclusive of the base type ''{1}''.
        minLength-less-than-equal-to-maxLength = minLength-less-than-equal-to-maxLength: In the definition of {2}, value of minLength = ''{0}'' must be < value of maxLength = ''{1}''.
        minLength-valid-restriction = minLength-valid-restriction: In the definition of {2}, minLength = ''{0}'' must be >= than that of the base type, ''{1}''.
        no-xmlns = no-xmlns: The {name} of an attribute declaration must not match 'xmlns'.
        no-xsi = no-xsi: The '{'target namespace'}' of an attribute declaration must not match ''{0}''.
        p-props-correct.2.1 = p-props-correct.2.1: In the declaration of ''{0}'', the value of ''minOccurs'' is ''{1}'', but it must not be greater than the value of ''maxOccurs'', which is ''{2}''.
        rcase-MapAndSum.1 = rcase-MapAndSum.1: There is not a complete functional mapping between the particles.
        rcase-MapAndSum.2 = rcase-MapAndSum.2: Group''s occurrence range, ({0},{1}), is not a valid restriction of base group''s occurrence range, ({2},{3}).
        rcase-NameAndTypeOK.1 = rcase-NameAndTypeOK.1: Elements have names and target namespaces which are not the same:  Element ''{0}'' in namespace ''{1}'' and element ''{2}'' in namespace ''{3}''.
        rcase-NameAndTypeOK.2 = rcase-NameAndTypeOK.2: Error for the particle whose '{'term'}' is the element declaration ''{0}''. The element declaration''s '{'nillable'}' is true, but the corresponding particle in the base type has an element declaration whose '{'nillable'}' is false.
        rcase-NameAndTypeOK.3 = rcase-NameAndTypeOK.3: Error for the particle whose '{'term'}' is the element declaration ''{0}''. Its occurrence range, ({1},{2}), is not a valid restriction of the range, ({3},{4}), of the corresponding particle in the base type.
        rcase-NameAndTypeOK.4.a = rcase-NameAndTypeOK.4.a: Element ''{0}'' is not fixed, but the corresponding element in the base type is fixed with value ''{1}''.
        rcase-NameAndTypeOK.4.b = rcase-NameAndTypeOK.4.b: Element ''{0}'' is fixed with value ''{1}'', but the corresponding element in the base type is fixed with value ''{2}''.
        rcase-NameAndTypeOK.5 = rcase-NameAndTypeOK.5: Identity constraints for element ''{0}'' are not a subset of those in base.
        rcase-NameAndTypeOK.6 = rcase-NameAndTypeOK.6: The disallowed substitutions for element ''{0}'' are not a superset of those in the base.
        rcase-NameAndTypeOK.7 = rcase-NameAndTypeOK.7: The type of element ''{0}'', ''{1}'', is not derived from the type of the base element, ''{2}''.
        rcase-NSCompat.1 = rcase-NSCompat.1: Element ''{0}'' has a namespace ''{1}'' which is not allowed by the wildcard in the base.
        rcase-NSCompat.2 = rcase-NSCompat.2: Error for the particle whose '{'term'}' is the element declaration ''{0}''. Its occurrence range, ({1},{2}), is not a valid restriction of the range, ({3},{4}), of the corresponding particle in the base type.
        rcase-NSRecurseCheckCardinality.1 = rcase-NSRecurseCheckCardinality.1: There is not a complete functional mapping between the particles.
        rcase-NSRecurseCheckCardinality.2 = rcase-NSRecurseCheckCardinality.2: Group''s occurrence range, ({0},{1}), is not a valid restriction of base wildcard''s range, ({2},{3}).
        rcase-NSSubset.1 = rcase-NSSubset.1: Wildcard is not a subset of corresponding wildcard in base.
        rcase-NSSubset.2 = rcase-NSSubset.2: Wildcard''s occurrence range, ({0},{1}), is not a valid restriction of that in the base, ({2},{3}),.
        rcase-NSSubset.3 = rcase-NSSubset.3: Wildcard''s process contents, ''{0}'', is weaker than that in the base, ''{1}''.
        rcase-Recurse.1 = rcase-Recurse.1: Group''s occurrence range, ({0},{1}), is not a valid restriction of base group''s occurrence range, ({2},{3}).
        rcase-Recurse.2 = rcase-Recurse.2: There is not a complete functional mapping between the particles.
        rcase-RecurseLax.1 = rcase-RecurseLax.1: Group''s occurrence range, ({0},{1}), is not a valid restriction of base group''s occurrence range, ({2},{3}).
        rcase-RecurseLax.2 = rcase-RecurseLax.2: There is not a complete functional mapping between the particles.
        rcase-RecurseUnordered.1 = rcase-RecurseUnordered.1: Group''s occurrence range, ({0},{1}), is not a valid restriction of base group''s occurrence range, ({2},{3}).
        rcase-RecurseUnordered.2 = rcase-RecurseUnordered.2: There is not a complete functional mapping between the particles.
#        We're using sch-props-correct.2 instead of the old src-redefine.1
#        src-redefine.1 = src-redefine.1: The component ''{0}'' is begin redefined, but its corresponding component isn't in the schema document being redefined (with namespace ''{2}''), but in a different document, with namespace ''{1}''.
        sch-props-correct.2 = sch-props-correct.2: A schema cannot contain two global components with the same name; this schema contains two occurrences of ''{0}''.
        st-props-correct.2 = st-props-correct.2: Circular definitions have been detected for simple type ''{0}''. This means that ''{0}'' is contained in its own type hierarchy, which is an error.
        st-props-correct.3 = st-props-correct.3: Error for type ''{0}''. The value of '{'final'}' of the '{'base type definition'}', ''{1}'', forbids derivation by restriction.
        totalDigits-valid-restriction = totalDigits-valid-restriction: In the definition of {2}, the value ''{0}'' for the facet ''totalDigits'' is invalid, because it must be <= the value for ''totalDigits'' which was set to ''{1}'' in one of the ancestor types.
        whiteSpace-valid-restriction.1 = whiteSpace-valid-restriction.1: In the definition of {0}, the value ''{1}'' for the facet ''whitespace'' is invalid, because the value for ''whitespace'' has been set to ''collapse'' in one of the ancestor types.
        whiteSpace-valid-restriction.2 = whiteSpace-valid-restriction.2: In the definition of {0}, the value ''preserve'' for the facet ''whitespace'' is invalid, because the value for ''whitespace'' has been set to ''replace'' in one of the ancestor types.

#schema for Schemas

        s4s-att-invalid-value = s4s-att-invalid-value: Invalid attribute value for ''{1}'' in element ''{0}''. Recorded reason: {2}
        s4s-att-must-appear = s4s-att-must-appear: Attribute ''{1}'' must appear in element ''{0}''.
        s4s-att-not-allowed = s4s-att-not-allowed: Attribute ''{1}'' cannot appear in element ''{0}''.
        s4s-elt-invalid = s4s-elt-invalid: Element ''{0}'' is not a valid element in a schema document.
        s4s-elt-must-match.1 = s4s-elt-must-match.1: The content of ''{0}'' must match {1}. A problem was found starting at: {2}.
        s4s-elt-must-match.2 = s4s-elt-must-match.2: The content of ''{0}'' must match {1}. Not enough elements were found.
        # the "invalid-content" messages provide less information than the "must-match" counterparts above. They're used for complex types when providing a "match" would be an information dump
        s4s-elt-invalid-content.1 = s4s-elt-invalid-content.1: The content of ''{0}'' is invalid.  Element ''{1}'' is invalid, misplaced, or occurs too often.
        s4s-elt-invalid-content.2 = s4s-elt-invalid-content.2: The content of ''{0}'' is invalid.  Element ''{1}'' cannot be empty.
        s4s-elt-invalid-content.3 = s4s-elt-invalid-content.3: Elements of type ''{0}'' cannot appear after declarations as children of a <schema> element.
        s4s-elt-schema-ns = s4s-elt-schema-ns: The namespace of element ''{0}'' must be from the schema namespace, ''http://www.w3.org/2001/XMLSchema''.
        s4s-elt-character = s4s-elt-character: Non-whitespace characters are not allowed in schema elements other than ''xs:appinfo'' and ''xs:documentation''. Saw ''{0}''.

# codes not defined by the spec

        c-fields-xpaths = c-fields-xpaths: The field value = ''{0}'' is not valid.
        c-general-xpath = c-general-xpath: The expression ''{0}'' is not valid with respect to the XPath subset supported by XML Schema.
        c-general-xpath-ns = c-general-xpath-ns: A namespace prefix in XPath expression ''{0}'' was not bound to a namespace.
        c-selector-xpath = c-selector-xpath: The selector value = ''{0}'' is not valid; selector xpaths cannot contain attributes.
        EmptyTargetNamespace = EmptyTargetNamespace: In schema document ''{0}'', the value of the ''targetNamespace'' attribute cannot be an empty string.
        FacetValueFromBase = FacetValueFromBase: In the declaration of type ''{0}'', value ''{1}'' of facet ''{2}'' must be from the value space of the base type, ''{3}''.
        FixedFacetValue = FixedFacetValue: In the definition of {3}, the value ''{1}'' for the facet ''{0}'' is invalid, because the value for ''{0}'' has been set to ''{2}'' in one of the ancestor types, and '{'fixed'}' = true.
        InvalidRegex = InvalidRegex: Pattern value ''{0}'' is not a valid regular expression. The reported error was: ''{1}''.
        maxOccurLimit = Current configuration of the parser doesn''t allow the expansion of a content model for a complex type to contain more than {0} nodes.
        PublicSystemOnNotation = PublicSystemOnNotation: At least one of 'public' and 'system' must appear in element 'notation'.
        SchemaLocation = SchemaLocation: schemaLocation value = ''{0}'' must have even number of URI''s.
        TargetNamespace.1 = TargetNamespace.1: Expecting namespace ''{0}'', but the target namespace of the schema document is ''{1}''.
        TargetNamespace.2 = TargetNamespace.2: Expecting no namespace, but the schema document has a target namespace of ''{1}''.
        UndeclaredEntity = UndeclaredEntity: Entity ''{0}'' is not declared.
        UndeclaredPrefix = UndeclaredPrefix: Cannot resolve ''{0}'' as a QName: the prefix ''{1}'' is not declared.

# JAXP 1.2 schema source property errors

        jaxp12-schema-source-type.1 = The ''http://java.sun.com/xml/jaxp/properties/schemaSource'' property cannot have a value of type ''{0}''. Possible types of the value supported are String, File, InputStream, InputSource or an array of these types.
        jaxp12-schema-source-type.2 = The ''http://java.sun.com/xml/jaxp/properties/schemaSource'' property cannot have an array value of type ''{0}''. Possible types of the array supported are Object, String, File, InputStream and InputSource.
        jaxp12-schema-source-ns = When using an array of Objects as the value of the 'http://java.sun.com/xml/jaxp/properties/schemaSource' property, it is illegal to have two schemas that share the same target namespace.
*/
    // @formatter:on
}

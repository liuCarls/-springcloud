/*
 * Activiti Modeler component part of the Activiti project
 * Copyright 2005-2014 Alfresco Software, Ltd. All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
'use strict';

var KISBPM = KISBPM || {};
KISBPM.PROPERTY_CONFIG =
{
    "string": {
        "readModeTemplateUrl": "editor-app/configuration/properties/default-value-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/string-property-write-mode-templates.html"
    },
    "boolean": {
        "templateUrl": "editor-app/configuration/properties/boolean-property-templates.html"
    },
    "text" : {
        "readModeTemplateUrl": "editor-app/configuration/properties/default-value-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/text-property-write-templates.html"
    },
    "kisbpm-multiinstance" : {
        "readModeTemplateUrl": "editor-app/configuration/properties/default-value-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/multiinstance-property-write-templates.html"
    },
    "oryx-formproperties-complex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/form-properties-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/form-properties-write-templates.html"
    },
    "oryx-executionlisteners-multiplecomplex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/execution-listeners-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/execution-listeners-write-templates.html"
    },
    "oryx-tasklisteners-multiplecomplex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/task-listeners-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/task-listeners-write-templates.html"
    },
    "oryx-eventlisteners-multiplecomplex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/event-listeners-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/event-listeners-write-templates.html"
    },
    "oryx-usertaskassignment-complex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/assignment-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/assignment-write-templates.html"
    },
    "oryx-servicetaskfields-complex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/fields-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/fields-write-templates.html"
    },
    "oryx-callactivityinparameters-complex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/in-parameters-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/in-parameters-write-templates.html"
    },
    "oryx-callactivityoutparameters-complex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/out-parameters-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/out-parameters-write-templates.html"
    },
    "oryx-subprocessreference-complex": {
        "readModeTemplateUrl": "editor-app/configuration/properties/subprocess-reference-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/subprocess-reference-write-templates.html"
    },
    "oryx-sequencefloworder-complex" : {
        "readModeTemplateUrl": "editor-app/configuration/properties/sequenceflow-order-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/sequenceflow-order-write-templates.html"
    },
    "oryx-conditionsequenceflow-complex" : {
        "readModeTemplateUrl": "editor-app/configuration/properties/condition-expression-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/condition-expression-write-templates.html"
    },
    "oryx-signaldefinitions-multiplecomplex" : {
        "readModeTemplateUrl": "editor-app/configuration/properties/signal-definitions-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/signal-definitions-write-templates.html"
    },
    "oryx-signalref-string" : {
        "readModeTemplateUrl": "editor-app/configuration/properties/default-value-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/signal-property-write-templates.html"
    },
    "oryx-messagedefinitions-multiplecomplex" : {
        "readModeTemplateUrl": "editor-app/configuration/properties/message-definitions-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/message-definitions-write-templates.html"
    },
    "oryx-messageref-string" : {
        "readModeTemplateUrl": "editor-app/configuration/properties/default-value-display-templates.html",
        "writeModeTemplateUrl": "editor-app/configuration/properties/message-property-write-templates.html"
    }
};

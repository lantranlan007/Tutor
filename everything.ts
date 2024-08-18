explain methods:
 /**
   * Get form template initialized with all inputs for given baseline object type.
   */
  static getFormTemplate(object: BaselineObject, action: string, moduleInterfaceFeatureEnabled?: boolean): DirectForm {

    let template: DirectForm = {
      value: object,
      title: BaselineObjectForm.getFormActionLabel(action),
      titleInfo: object.category.label,
      objectInfo: object.displayName,
      tutorialType: null,
      inputs: []
    };

    //
    template = BaselineObjectForm.method(template, object, moduleInterfaceFeatureEnabled);

    if (object.category.requirementsEnabled) {
      // initRequirementsTemplate();
      const formArray = {
        type: 'form-array',
        name: 'requirements',
        label: 'LABEL.REQUIREMENTS',
        initialValue: [],
        sortable: false,
        inputs: new RequirementFormTemplate().inputs
      };
      template.inputs.push(formArray);
    }
    return template;
  }

// noinspection OverlyComplexFunctionJS
  static method(template, object, moduleInterfaceFeatureEnabled?: boolean): DirectForm {
    switch (object.category) {
      case ObjectCategory.pin:
        return BaselineObjectForm.initPinTemplate(template);
      case ObjectCategory.signal:
        return BaselineObjectForm.initSignalTemplate(template);
      case ObjectCategory.component:
        return BaselineObjectForm.initSCEConTypeComponentTemplate(template);
      case ObjectCategory.subsystem:
        return BaselineObjectForm.initSubsystemTemplate(template);
      case ObjectCategory.distributionPoint:
        return BaselineObjectForm.initDistributionPointTemplate(template);
      case ObjectCategory.functionModule:
        return BaselineObjectForm.initFunctionModuleTemplate(template, object, moduleInterfaceFeatureEnabled);
      case ObjectCategory.function:
        return BaselineObjectForm.initFunctionTemplate(template);
      case ObjectCategory.layoutModule:
        return BaselineObjectForm.initLayoutModule(template);
      default:
        return BaselineObjectForm.initDefaultTemplate(template);
    }
  }

  static initDefaultTemplate(template: DirectForm): DirectForm {
    template.inputs = [
      {
        type: 'dropdown',
        name: 'containerDirection',
        label: 'LABEL.BLOCKDIAGRAM_SECTION',
        options: [
          {label: 'LABEL.INPUT', value: DIR_INPUT},
          {label: 'LABEL.OUTPUT', value: DIR_OUTPUT},
          {label: 'LABEL.INTERNAL', value: DIR_INTERNAL}
        ],
        clazz: 'p-col-6',

      },
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME_BOSCH',
        validators: [Validators.required],
        errorMessage: {
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthNameFull,
        autofocus: true,
        clazz: 'p-col-9'
      },
      {
        type: 'input',
        name: 'index',
        label: 'LABEL.INDEX',
        validators: [Validators.required],
        errorMessage: {
          'invalidIndexError': 'MSG.ERR_INVALID_INDEX',
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthIndex,
        format: 'int',
        clazz: 'p-col-6',

      },
      {
        type: 'input',
        name: 'nameCustomer',
        label: 'LABEL.NAME_CUSTOMER',
        maxlength: maxlengthNameFull,
        clazz: 'p-col-12',
        sectionId: 'CUSTOMER'
      },
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.REMARK',
        clazz: 'p-col-12',

      }
    ];
    return template;
  }

  static initPinTemplate(template: DirectForm): DirectForm {
    template.width = dialogWidthLarge;
    template.height = dialogHeightLarge;
    template.tutorialType = 'PIN';
    this.initDirectFormSection(template);
    template.inputs = [
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.DESCRIPTION',
        autofocus: true,
        clazz: 'p-col-12',

      },
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME_BOSCH',
        readonly: true,
        clazz: 'p-col-12',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'nameCustomer',
        label: 'LABEL.NAME_CUSTOMER',
        readonly: true,
        clazz: 'p-col-12',
        sectionId: 'CUSTOMER'
      }
    ];
    return template;
  }

  static initSignalTemplate(template: DirectForm): DirectForm {
    this.initDirectFormSection(template);
    template.width = dialogWidthLarge;
    template.height = dialogHeightXLarge;
    template.tutorialType = 'SIGNAL';
    template.inputs = [
      {
        type: 'dropdown',
        name: 'containerDirection',
        label: 'LABEL.BLOCKDIAGRAM_SECTION',
        options: [
          {label: 'LABEL.INPUT', value: DIR_INPUT},
          {label: 'LABEL.OUTPUT', value: DIR_OUTPUT}
        ],
        sectionId: 'SEARCH'
      },
      {
        type: 'dropdown',
        name: 'signalDirection',
        label: 'LABEL.DIRECTION',
        validators: [Validators.required],
        options: [SignalDirection.input, SignalDirection.bidirectional, SignalDirection.output],
        clazz: 'p-col-4',
        disabled: true,
      },
      {
        type: 'dropdown',
        name: 'signalTypeCode',
        label: 'LABEL.SIGNAL_TYPE',
        validators: [Validators.required],
        options: [],
        clazz: 'p-col-4',
        disabled: true,
      },
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME_BOSCH_DE',
        validators: [Validators.required],
        maxlength: maxlengthNameFull,
        format: formatSignalNames,
        clazz: 'p-col-4',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'nameBoschEn',
        label: 'LABEL.NAME_BOSCH_EN',
        validators: [Validators.required],
        maxlength: maxlengthNameFull,
        format: formatSignalNames,
        clazz: 'p-col-4',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'index',
        label: 'LABEL.INDEX',
        validators: [Validators.required],
        maxlength: maxlengthIndex,
        format: 'int',
        clazz: 'p-col-4',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'descriptionBoschDe',
        label: 'LABEL.DESCRIPTION_BOSCH_DE',
        clazz: 'p-col-12',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'descriptionBoschEn',
        label: 'LABEL.DESCRIPTION_BOSCH_EN',
        clazz: 'p-col-12',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'nameCustomer',
        label: 'LABEL.NAME_CUSTOMER_SHORT',
        maxlength: maxlengthNameFull,
        format: formatSignalNames,
        clazz: 'p-col-4',
        sectionId: 'CUSTOMER'
      },
      {
        type: 'input',
        name: 'indexCustomer',
        label: 'LABEL.INDEX_CUSTOMER',
        maxlength: maxlengthCustomerIndex,
        clazz: 'p-col-4',
        sectionId: 'CUSTOMER'
      },
      {
        type: 'input',
        name: 'descriptionCustomer',
        label: 'LABEL.DESCRIPTION_CUSTOMER',
        maxlength: maxlengthDescription,
        clazz: 'p-col-12',
        sectionId: 'CUSTOMER'
      },
      {
        type: 'hidden',
        name: 'isSignalRegistered',
        label: ''
      }
    ];
    return template;
  }

  static initSCEConTypeComponentTemplate(template: DirectForm): DirectForm {
    this.initDirectFormSection(template);
    template.width = dialogWidthXLarge;
    template.height = dialogHeightXLarge;
    template.tutorialType = 'SYSTEM_COMPONENT';
    template.inputs = [
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME_BOSCH_SHORT',
        validators: [Validators.required],
        errorMessage: {
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthNameShort,
        autofocus: true,
        clazz: 'p-col-8',

      },
      {
        type: 'input',
        name: 'index',
        label: 'LABEL.INDEX',
        validators: [Validators.required],
        errorMessage: {
          'invalidIndexError': 'MSG.ERR_INVALID_INDEX',
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthIndex,
        format: 'int',
        clazz: 'p-col-4',

      },
      {
        type: 'input',
        name: 'fullNameBosch',
        label: 'LABEL.NAME_BOSCH',
        validators: [Validators.required],
        maxlength: maxlengthNameFull,
        clazz: 'p-col-12',

      },
      {
        type: 'input',
        name: 'nameCustomer',
        label: 'LABEL.NAME_CUSTOMER_SHORT',
        maxlength: maxlengthNameShort,
        clazz: 'p-col-8',

      },
      {
        type: 'input',
        name: 'indexCustomer',
        label: 'LABEL.INDEX_CUSTOMER',
        maxlength: maxlengthCustomerIndex,
        clazz: 'p-col-4',

      },
      {
        type: 'input',
        name: 'fullNameCustomer',
        label: 'LABEL.NAME_CUSTOMER',
        maxlength: maxlengthNameFull,
        clazz: 'p-col-12',
        sectionId: 'CUSTOMER'
      },
      {
        type: 'input',
        name: 'manufacturer',
        label: 'LABEL.MANUFACTURER',
        clazz: 'p-col-12',

      },
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.DESCRIPTION',
        clazz: 'p-col-12',

      },
      {
        type: 'multiselect',
        name: 'subsystem',
        label: 'LABEL.SUBSYSTEM',
        limit: 1,

      },
      {
        type: 'multiselect',
        name: 'symbols',
        label: 'LABEL.PLU.SYMBOL',

      },
      {
        type: 'form-array',
        name: 'connections',
        label: 'LABEL.PLU.CONNECTION',
        validators: [DirectArrayValidators.uniqueBy('number')],
        errorMessage: {uniqueArrayError: 'MSG.ERR_PIN_NR_NOT_UNIQUE'},
        initialValue: [],
        header: [{
          label: 'LABEL.PIN_NR',
          clazz: 'p-col-1'
        }, {
          label: 'LABEL.NAME',
          clazz: 'p-col-4'
        }, {
          label: 'LABEL.CONNECTION_TYPE',
          clazz: 'p-col-5'
        }, {
          label: 'LABEL.SPECIAL_FUNCTION',
          clazz: 'p-col-2'
        }],
        inputs: [
          {
            type: 'input',
            name: 'number',
            label: '',
            placeholder: 'LABEL.PIN_NR',
            maxlength: maxlengthIndex,
            format: formatAlphanumeric,
            validators: [Validators.required],
            clazz: 'p-col-1'
          },
          {
            type: 'input',
            name: 'name',
            label: '',
            placeholder: 'LABEL.NAME',
            clazz: 'p-col-4'
          },
          {
            type: 'checkboxdialog',
            name: 'specialFunctionRemark',
            label: 'LABEL.SPECIAL_FUNCTION_REMARK',
            clazz: 'p-col-2 p-d-flex p-ai-center p-jc-center',
            required: true
          },
          {
            type: 'hidden',
            name: 'hasSpecialFunction',
            label: ''
          },
          {type: 'hidden', name: 'position', label: ''}
        ],
        sortable: true
      }


    ];
    return template;
  }

  static initComponentTemplate(template: DirectForm): DirectForm {
    this.initDirectFormSection(template);
    template.width = dialogWidthLarge;
    template.height = dialogHeightXLarge;
    template.tutorialType = 'SYSTEM_COMPONENT';
    template.inputs = [
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME_BOSCH_SHORT',
        validators: [Validators.required],
        errorMessage: {
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthNameShort,
        autofocus: true,
        clazz: 'p-col-8',

      },
      {
        type: 'input',
        name: 'index',
        label: 'LABEL.INDEX',
        validators: [Validators.required],
        errorMessage: {
          'invalidIndexError': 'MSG.ERR_INVALID_INDEX',
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthIndex,
        format: 'int',
        clazz: 'p-col-4',

      },
      {
        type: 'input',
        name: 'fullNameBosch',
        label: 'LABEL.NAME_BOSCH',
        validators: [Validators.required],
        maxlength: maxlengthNameFull,
        clazz: 'p-col-12',

      },
      {
        type: 'input',
        name: 'nameCustomer',
        label: 'LABEL.NAME_CUSTOMER_SHORT',
        maxlength: maxlengthNameShort,
        clazz: 'p-col-8',

      },
      {
        type: 'input',
        name: 'indexCustomer',
        label: 'LABEL.INDEX_CUSTOMER',
        maxlength: maxlengthCustomerIndex,
        clazz: 'p-col-4',

      },
      {
        type: 'input',
        name: 'fullNameCustomer',
        label: 'LABEL.NAME_CUSTOMER',
        maxlength: maxlengthNameFull,
        clazz: 'p-col-12',
        sectionId: 'CUSTOMER'
      },
      {
        type: 'input',
        name: 'manufacturer',
        label: 'LABEL.MANUFACTURER',
        clazz: 'p-col-12',

      },
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.DESCRIPTION',
        clazz: 'p-col-12',

      },
      {
        type: 'multiselect',
        name: 'subsystem',
        label: 'LABEL.SUBSYSTEM',
        limit: 1,

      },
      {
        type: 'multiselect',
        name: 'symbols',
        label: 'LABEL.PLU.SYMBOL',

      },
      {
        type: 'form-array',
        name: 'connections',
        label: 'LABEL.PLU.CONNECTION',
        validators: [DirectArrayValidators.uniqueBy('number')],
        errorMessage: {uniqueArrayError: 'MSG.ERR_PIN_NR_NOT_UNIQUE'},
        initialValue: [],
        header: [{
          label: 'LABEL.PIN_NR',
          clazz: 'p-col-2'
        }, {
          label: 'LABEL.NAME',
          clazz: 'p-col-5'
        }, {
          label: 'LABEL.CONNECTION_COMP_PLACEHOLDER',
          clazz: 'p-col-5'
        }],
        inputs: [
          {
            type: 'input',
            name: 'number',
            label: '',
            placeholder: 'LABEL.PIN_NR',
            maxlength: maxlengthIndex,
            format: formatAlphanumeric,
            validators: [Validators.required],
            clazz: 'p-col-2'
          },
          {
            type: 'input',
            name: 'name',
            label: '',
            placeholder: 'LABEL.NAME',
            clazz: 'p-col-5'
          },
          {
            type: 'dropdown',
            name: 'ioType',
            label: '',
            placeholder: 'LABEL.CONNECTION_COMP_PLACEHOLDER',
            clazz: 'p-col-5'
          },
          {type: 'hidden', name: 'position', label: ''}
        ],
        sortable: true
      }


    ];
    return template;
  }

  static initSubsystemTemplate(template: DirectForm): DirectForm {
    template.height = dialogHeightSmall;
    template.width = dialogWidthLarge;
    template.tutorialType = 'SUBSYSTEM';
    template.inputs = [
      {
        type: 'autocomplete',
        name: 'nameBosch',
        label: 'LABEL.NAME',
        validators: [Validators.required],
        errorMessage: {'uniqueCombinationError': 'MSG.ERR_NAME_NOT_UNIQUE'},
        maxlength: maxlengthNameFull,
        suggestions: [],
        panelClazz: 'maxHeight',
        autofocus: true,
        clazz: 'p-col-12',
      },
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.DESCRIPTION',
        clazz: 'p-col-12',

      }
    ];
    return template;
  }

  static initDistributionPointTemplate(template: DirectForm): DirectForm {
    template.height = dialogHeightLarge;
    template.width = dialogWidthLarge;
    template.tutorialType = 'DIST_POINT';
    template.inputs = [
      {
        type: 'input',
        name: 'identifier',
        label: 'LABEL.IDENTIFIER',
        validators: [Validators.required],
        errorMessage: {'uniqueCombinationError': 'MSG.ERR_DP_NOT_UNIQUE'},
        maxlength: maxlengthIndex,
        autofocus: true,
        clazz: 'p-col-12',
      },
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME',
        validators: [Validators.required],
        maxlength: maxlengthNameShort,
        clazz: 'p-col-12',
      },
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.DESCRIPTION',
        maxlength: maxlengthDescription,
        clazz: 'p-col-12',
      },
      {
        type: 'hidden',
        name: 'type',
        label: '',
      },
      {
        type: 'hidden',
        name: 'symbol',
        label: '',

      }
    ];
    return template;
  }

  static getFormActionLabel(formAction: string): string {
    switch (formAction) {
      case ActionType.CREATE:
        return 'LABEL.ADD';
      case ActionType.EDIT:
        return 'LABEL.EDIT';
      case ActionType.DERIVE:
        return 'LABEL.DERIVE';
      default:
        return '';
    }
  }

  static initFunctionModuleTemplate(template: DirectForm, object: BaselineObject, moduleInterfaceFeatureEnabled?: boolean): DirectForm {
    this.initDirectFormSection(template);
    template.height = functionModuleDialogHeight;
    template.width = moduleInterfaceFeatureEnabled ? window.innerWidth * widthRatio : window.innerWidth / 3;
    template.tutorialType = 'FUNCTION_MODULE';
    template.isMaximizable = true;

    const module = object as BaselineFunctionModule;
    if (!ObjectUtils.isNullOrUndefined(module.refId)) {
      template.statusInfo = ' - ' + module.modaState;
      if (EadmModuleUtils.isModuleApp(module.codIdRefTable)) {
        template.usageState = module.usageState;
      }
    }
    template.inputs = [
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.DESCRIPTION',
        clazz: 'p-col-12',

      },
      {
        type: 'dropdown',
        name: 'containerDirection',
        label: 'LABEL.BLOCKDIAGRAM_SECTION',
        options: [
          {label: 'LABEL.INPUT', value: DIR_INPUT},
          {label: 'LABEL.OUTPUT', value: DIR_OUTPUT},
          {label: 'LABEL.INTERNAL', value: DIR_INTERNAL}
        ],
        clazz: 'p-col-6',

      },
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME_BOSCH',
        validators: [Validators.required],
        errorMessage: {
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthNameFull,
        autofocus: true,
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'index',
        label: 'LABEL.INDEX',
        validators: [Validators.required],
        errorMessage: {
          'invalidIndexError': 'MSG.ERR_INVALID_INDEX',
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthIndex,
        format: 'int',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'modifiedComment',
        label: 'LABEL.MODIFICATION_COMMENT',
        sectionId: 'RB'
      },
      {
        type: 'checkbox',
        name: 'modified',
        label: 'LABEL.MODIFIED',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'nameCustomer',
        label: 'LABEL.NAME_CUSTOMER',
        maxlength: maxlengthNameFull,
        clazz: 'p-col-12',
        sectionId: 'CUSTOMER'
      },
      {
        type: 'hidden',
        name: 'refId',
        label: '',
      },
      {
        type: 'hidden',
        name: 'codIdRefTable',
        label: '',
      },
      {
        type: 'hidden',
        name: 'modihId',
        label: '',
      },
      {
        type: 'hidden',
        name: 'cost',
        label: '',
      },
      {
        type: 'hidden',
        name: 'modaState',
        label: '',
      },
      {
        type: 'hidden',
        name: 'usageState',
        label: '',
      },
      {
        type: 'hidden',
        name: 'interfacesOutdated',
        label: '',
      },
      {
        type: 'hidden',
        name: 'businessUnitOutdated',
        label: '',
      },
      {
        type: 'checkbox',
        name: 'showInterfaces',
        label: 'LABEL.SHOW_INTERFACES',
        sectionId: 'INTERFACES'
      },
      {
        type: 'form-array',
        name: 'interfaces',
        label: 'LABEL.PLU.INTERFACES',
        validators: [],
        errorMessage: {uniqueArrayError: 'MSG.ERR_PIN_NR_NOT_UNIQUE'},
        initialValue: [],
        header: [{
          label: 'LABEL.NAME',
          clazz: 'p-col-4'
        }, {
          label: 'LABEL.DISPLAYNAME',
          clazz: 'p-col-4'
        }, {
          label: 'LABEL.ASSIGNED_SINGAL_PIN',
          clazz: 'p-col-4'
        }],
        inputs: [
          {
            label: '',
            type: 'input',
            name: 'name',
            placeholder: 'LABEL.NETNAME',
            validators: [Validators.required],
            clazz: 'p-col-4'
          },
          {
            type: 'input',
            name: 'displayNetname',
            label: '',
            placeholder: 'LABEL.DISPLAYNAME',
            clazz: 'p-col-4',
            isResetButtonShown: true,
            resetButtonTooltip: 'LABEL.RECALCULATE_DEFAULT',
            defaultValueField: 'defaultDisplayName',
            validators: [Validators.required]
          },
          {
            type: 'hidden',
            name: 'defaultDisplayName',
            label: ''
          },
          {
            type: 'hidden',
            name: 'styleClasses',
            label: ''
          },
          {
            type: 'hidden',
            name: 'tooltip',
            label: ''
          },
          {
            type: 'dropdown',
            name: 'assignedPinSignal',
            placeholder: 'LABEL.ASSIGNED_SINGAL_PIN',
            isResetButtonShown: true,
            options: [],
            clazz: 'p-col-4',
            label: ''
          },
          {
            type: 'hidden',
            name: 'modinId',
            label: ''
          },
          {
            type: 'hidden',
            name: 'parts',
            label: ''
          },
          {
            type: 'hidden',
            name: 'modihIdOri',
            label: ''
          }
        ],
        sortable: false
      }
    ];
    return template;
  }


  static initFunctionTemplate(template: DirectForm): DirectForm {
    this.initDirectFormSection(template);
    template.height = dialogHeightLarge;
    template.width = dialogWidthLarge;
    template.tutorialType = 'FUNCTION';
    template.inputs = [
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.DESCRIPTION',
        clazz: 'p-col-12',

      },
      {
        type: 'dropdown',
        name: 'containerDirection',
        label: 'LABEL.BLOCKDIAGRAM_SECTION',
        options: [
          {label: 'LABEL.INPUT', value: DIR_INPUT},
          {label: 'LABEL.OUTPUT', value: DIR_OUTPUT},
          {label: 'LABEL.INTERNAL', value: DIR_INTERNAL}
        ],
        clazz: 'p-col-6',

      },
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME_BOSCH',
        validators: [Validators.required],
        maxlength: maxlengthNameFull,
        autofocus: true,
        clazz: 'p-col-9',
        sectionId: 'RB',
      },
      {
        type: 'input',
        name: 'index',
        label: 'LABEL.INDEX',
        validators: [Validators.required],
        errorMessage: {
          'invalidIndexError': 'MSG.ERR_INVALID_INDEX',
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthIndex,
        clazz: 'p-col-9',
        sectionId: 'RB',
      },
      {
        type: 'input',
        name: 'nameCustomer',
        label: 'LABEL.NAME_CUSTOMER',
        maxlength: maxlengthNameFull,
        clazz: 'p-col-9',
        sectionId: 'CUSTOMER'
      }
    ];
    return template;
  }

  static initLayoutModule(template: DirectForm): DirectForm {
    this.initDirectFormSection(template);
    template.height = dialogHeightLarge;
    template.width = dialogWidthLarge;
    template.tutorialType = 'RESULTING_CIRCUIT';
    template.inputs = [
      {
        type: 'input',
        name: 'remark',
        label: 'LABEL.DESCRIPTION',
        clazz: 'p-col-12',

      },
      {
        type: 'dropdown',
        name: 'containerDirection',
        label: 'LABEL.BLOCKDIAGRAM_SECTION',
        options: [
          {label: 'LABEL.INPUT', value: DIR_INPUT},
          {label: 'LABEL.OUTPUT', value: DIR_OUTPUT},
          {label: 'LABEL.INTERNAL', value: DIR_INTERNAL}
        ],
        clazz: 'p-col-6',

      },
      {
        type: 'input',
        name: 'nameBosch',
        label: 'LABEL.NAME_BOSCH',
        validators: [Validators.required],
        errorMessage: {
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthNameFull,
        autofocus: true,
        clazz: 'p-col-9',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'index',
        label: 'LABEL.INDEX',
        validators: [Validators.required],
        errorMessage: {
          'invalidIndexError': 'MSG.ERR_INVALID_INDEX',
          'uniqueCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE',
          'uniqueAlreadySavedCombinationError': 'MSG.ERR_NAME_INDEX_NOT_UNIQUE_IN_DB'
        },
        maxlength: maxlengthIndex,
        format: 'int',
        clazz: 'p-col-9',
        sectionId: 'RB'
      },
      {
        type: 'input',
        name: 'nameCustomer',
        label: 'LABEL.NAME_CUSTOMER',
        maxlength: maxlengthNameFull,
        clazz: 'p-col-9',
        sectionId: 'CUSTOMER'
      }
    ];
    return template;
  }


  static initDirectFormSection(template: DirectForm): DirectForm {

    if (template.title.includes('ADD') || template.title.includes('DERIVE')) {
      template.sections = [{id: 'RB', title: 'LABEL.RB_DETAILS', category: 'bosch', selected: false, addExpanded: true},
        {id: 'CUSTOMER', title: 'LABEL.CUSTOMER_DETAILS', category: 'customer', selected: false, addExpanded: false}];
    } else {
      template.sections = [{
        id: 'RB',
        title: 'LABEL.RB_DETAILS',
        category: 'bosch',
        selected: false,
        addExpanded: false
      },
        {id: 'CUSTOMER', title: 'LABEL.CUSTOMER_DETAILS', category: 'customer', selected: false, addExpanded: false}];
    }
    return template;
  }

  static getRefDesignatorMaxUsageForm(maxUsage: {value: number}): DirectForm {
    return  {
      value: maxUsage,
      title: BaselineObjectForm.getFormActionLabel(ActionType.EDIT),
      titleInfo: 'LABEL.MAX_USAGE_HEADER',
      inputs: [
        {
          type: 'input',
          name: 'value',
          label: 'LABEL.MAX_USAGE',
          clazz: 'p-col-12 direct-pt-25',
          format: 'int',
          validators: [Validators.required, Validators.min(1), Validators.max(99), Validators.pattern('^[0-9]*$')],
          errorMessage: {
            'required': 'LABEL.FIELD_REQUIRED',
            'max': 'MSG.ONLY_2_DIGITS_ARE_ALLOWED',
            'min': 'MSG.NUMBER_MUST_BE_GREATER_THAN_0',
            'pattern': 'MSG.VALUE_SHOULD_BE_NUMBER'
          }
        }
      ],
      tutorialType: null,
      width: 450,
      height: 250
    };
  }
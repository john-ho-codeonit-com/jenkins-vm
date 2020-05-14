#!/usr/bin/env bash

multipass launch -n jenkins-vm --cloud-init cloud-init.yaml -v
